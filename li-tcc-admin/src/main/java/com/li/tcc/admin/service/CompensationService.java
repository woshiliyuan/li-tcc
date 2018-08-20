package com.li.tcc.admin.service;

import java.util.List;

import com.li.tcc.admin.page.CommonPager;
import com.li.tcc.admin.query.CompensationQuery;
import com.li.tcc.admin.vo.TccCompensationVO;

/**
 * compensation log Service
 * 
 * @author yuan.li
 */
public interface CompensationService {

	/**
	 * get TccCompensationVO by page
	 * 
	 * @param query
	 *            CompensationQuery
	 * @return CommonPager TransactionRecoverVO
	 */
	CommonPager<TccCompensationVO> listByPage(CompensationQuery query);

	/**
	 * batch remove transaction log by ids
	 *
	 * @param ids
	 *            ids pk ids
	 * @param appName
	 *            app name
	 * @return true success
	 */
	Boolean batchRemove(List<String> ids, String appName);

	/**
	 * modify retry count
	 *
	 * @param id
	 *            transId
	 * @param retry
	 *            retry
	 * @param appName
	 *            appName
	 * @return true success
	 */
	Boolean updateRetry(String id, Integer retry, String appName);
}
