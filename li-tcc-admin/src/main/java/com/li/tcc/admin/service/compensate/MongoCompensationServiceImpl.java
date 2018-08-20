package com.li.tcc.admin.service.compensate;

import com.li.tcc.common.bean.adapter.MongoAdapter;
import com.li.tcc.common.exception.TccRuntimeException;
import com.li.tcc.common.utils.DateUtils;
import com.li.tcc.common.utils.RepositoryPathUtils;

import com.li.tcc.admin.helper.ConvertHelper;
import com.li.tcc.admin.helper.PageHelper;
import com.li.tcc.admin.page.CommonPager;
import com.li.tcc.admin.page.PageParameter;
import com.li.tcc.admin.query.CompensationQuery;
import com.li.tcc.admin.service.CompensationService;
import com.li.tcc.admin.vo.TccCompensationVO;
import com.mongodb.client.result.UpdateResult;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Mongodb impl
 * 
 * @author yuan.li
 */
public class MongoCompensationServiceImpl implements CompensationService {

	private final MongoTemplate mongoTemplate;

	public MongoCompensationServiceImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public CommonPager<TccCompensationVO> listByPage(final CompensationQuery query) {
		CommonPager<TccCompensationVO> voCommonPager = new CommonPager<>();
		final String mongoTableName = RepositoryPathUtils.buildMongoTableName(query.getApplicationName());
		final PageParameter pageParameter = query.getPageParameter();
		final int pageSize = pageParameter.getPageSize();
		Query baseQuery = new Query();
		if (StringUtils.isNoneBlank(query.getTransId())) {
			baseQuery.addCriteria(new Criteria("transId").is(query.getTransId()));
		}
		if (Objects.nonNull(query.getRetry())) {
			baseQuery.addCriteria(new Criteria("retriedCount").lt(query.getRetry()));
		}
		final long totalCount = mongoTemplate.count(baseQuery, mongoTableName);
		if (totalCount <= 0) {
			return voCommonPager;
		}
		final int currentPage = pageParameter.getCurrentPage();
		int start = (currentPage - 1) * pageSize;
		voCommonPager.setPage(PageHelper.buildPage(query.getPageParameter(), (int) totalCount));
		baseQuery.skip(start).limit(pageSize);
		final List<MongoAdapter> mongoAdapters = mongoTemplate.find(baseQuery, MongoAdapter.class, mongoTableName);
		if (CollectionUtils.isNotEmpty(mongoAdapters)) {
			final List<TccCompensationVO> recoverVOS = mongoAdapters.stream().map(ConvertHelper::buildVO)
					.collect(Collectors.toList());
			voCommonPager.setDataList(recoverVOS);
		}
		return voCommonPager;
	}

	@Override
	public Boolean batchRemove(final List<String> ids, final String applicationName) {
		if (CollectionUtils.isEmpty(ids) || StringUtils.isBlank(applicationName)) {
			return Boolean.FALSE;
		}
		final String mongoTableName = RepositoryPathUtils.buildMongoTableName(applicationName);
		ids.forEach(id -> {
			Query query = new Query();
			query.addCriteria(new Criteria("transId").is(id));
			mongoTemplate.remove(query, mongoTableName);
		});
		return Boolean.TRUE;
	}

	@Override
	public Boolean updateRetry(final String id, final Integer retry, final String appName) {
		if (StringUtils.isBlank(id) || StringUtils.isBlank(appName) || Objects.isNull(retry)) {
			return Boolean.FALSE;
		}
		final String mongoTableName = RepositoryPathUtils.buildMongoTableName(appName);
		Query query = new Query();
		query.addCriteria(new Criteria("transId").is(id));
		Update update = new Update();
		update.set("lastTime", DateUtils.getCurrentDateTime());
		update.set("retriedCount", retry);
		final UpdateResult updateResult = mongoTemplate.updateFirst(query, update, MongoAdapter.class, mongoTableName);
		if (updateResult.getModifiedCount() <= 0) {
			throw new TccRuntimeException("更新数据异常!");
		}
		return Boolean.TRUE;
	}

}
