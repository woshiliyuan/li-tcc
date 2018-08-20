package com.li.tcc.admin.service.compensate;

import com.google.common.collect.Sets;
import com.li.tcc.admin.helper.ConvertHelper;
import com.li.tcc.admin.helper.PageHelper;
import com.li.tcc.admin.page.CommonPager;
import com.li.tcc.admin.query.CompensationQuery;
import com.li.tcc.admin.service.CompensationService;
import com.li.tcc.admin.vo.TccCompensationVO;

import com.li.tcc.common.bean.adapter.CoordinatorRepositoryAdapter;
import com.li.tcc.common.exception.TccException;
import com.li.tcc.common.jedis.JedisClient;
import com.li.tcc.common.serializer.ObjectSerializer;
import com.li.tcc.common.utils.DateUtils;
import com.li.tcc.common.utils.RepositoryPathUtils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * redis impl
 * 
 * @author yuan.li
 */
public class RedisCompensationServiceImpl implements CompensationService {

	private final JedisClient jedisClient;

	private final ObjectSerializer objectSerializer;

	public RedisCompensationServiceImpl(JedisClient jedisClient, ObjectSerializer objectSerializer) {
		this.jedisClient = jedisClient;
		this.objectSerializer = objectSerializer;
	}

	@Override
	public CommonPager<TccCompensationVO> listByPage(final CompensationQuery query) {
		CommonPager<TccCompensationVO> commonPager = new CommonPager<>();
		final String redisKeyPrefix = RepositoryPathUtils.buildRedisKeyPrefix(query.getApplicationName());
		final int currentPage = query.getPageParameter().getCurrentPage();
		final int pageSize = query.getPageParameter().getPageSize();
		int start = (currentPage - 1) * pageSize;
		Set<byte[]> keys;
		List<TccCompensationVO> voList;
		int totalCount;
		// 如果只查 重试条件的
		if (StringUtils.isBlank(query.getTransId()) && Objects.nonNull(query.getRetry())) {
			keys = jedisClient.keys((redisKeyPrefix + "*").getBytes());
			final List<TccCompensationVO> all = findAll(keys);
			final List<TccCompensationVO> collect = all.stream().filter(vo -> vo.getRetriedCount() < query.getRetry())
					.collect(Collectors.toList());
			totalCount = collect.size();
			voList = collect.stream().skip(start).limit(pageSize).collect(Collectors.toList());
		} else if (StringUtils.isNoneBlank(query.getTransId()) && Objects.isNull(query.getRetry())) {
			keys = Sets.newHashSet(String.join(":", redisKeyPrefix, query.getTransId()).getBytes());
			totalCount = keys.size();
			voList = findAll(keys);
		} else if (StringUtils.isNoneBlank(query.getTransId()) && Objects.nonNull(query.getRetry())) {
			keys = Sets.newHashSet(String.join(":", redisKeyPrefix, query.getTransId()).getBytes());
			totalCount = keys.size();
			voList = findAll(keys).stream().filter(vo -> vo.getRetriedCount() < query.getRetry())
					.collect(Collectors.toList());
		} else {
			keys = jedisClient.keys((redisKeyPrefix + "*").getBytes());
			if (keys.size() <= 0 || keys.size() < start) {
				return commonPager;
			}
			totalCount = keys.size();
			voList = findByPage(keys, start, pageSize);
		}
		if (keys.size() <= 0 || keys.size() < start) {
			return commonPager;
		}
		commonPager.setPage(PageHelper.buildPage(query.getPageParameter(), totalCount));
		commonPager.setDataList(voList);
		return commonPager;
	}

	private TccCompensationVO buildVOByKey(final byte[] key) {
		final byte[] bytes = jedisClient.get(key);
		try {
			final CoordinatorRepositoryAdapter adapter = objectSerializer.deSerialize(bytes,
					CoordinatorRepositoryAdapter.class);
			return ConvertHelper.buildVO(adapter);
		} catch (TccException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean batchRemove(final List<String> ids, final String applicationName) {
		if (CollectionUtils.isEmpty(ids) || StringUtils.isBlank(applicationName)) {
			return Boolean.FALSE;
		}
		String keyPrefix = RepositoryPathUtils.buildRedisKeyPrefix(applicationName);
		final String[] keys = ids.stream().map(id -> RepositoryPathUtils.buildRedisKey(keyPrefix, id))
				.toArray(String[]::new);
		jedisClient.del(keys);
		return Boolean.TRUE;
	}

	@Override
	public Boolean updateRetry(final String id, final Integer retry, final String appName) {
		if (StringUtils.isBlank(id) || StringUtils.isBlank(appName) || Objects.isNull(retry)) {
			return Boolean.FALSE;
		}
		String keyPrefix = RepositoryPathUtils.buildRedisKeyPrefix(appName);
		final String key = RepositoryPathUtils.buildRedisKey(keyPrefix, id);
		final byte[] bytes = jedisClient.get(key.getBytes());
		try {
			final CoordinatorRepositoryAdapter adapter = objectSerializer.deSerialize(bytes,
					CoordinatorRepositoryAdapter.class);
			adapter.setRetriedCount(retry);
			adapter.setLastTime(DateUtils.getDateYYYY());
			jedisClient.set(key, objectSerializer.serialize(adapter));
			return Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}

	private List<TccCompensationVO> findAll(final Set<byte[]> keys) {
		return keys.parallelStream().map(this::buildVOByKey).filter(Objects::nonNull).collect(Collectors.toList());
	}

	private List<TccCompensationVO> findByPage(final Set<byte[]> keys, final int start, final int pageSize) {
		return keys.parallelStream().skip(start).limit(pageSize).map(this::buildVOByKey).filter(Objects::nonNull)
				.collect(Collectors.toList());
	}

}
