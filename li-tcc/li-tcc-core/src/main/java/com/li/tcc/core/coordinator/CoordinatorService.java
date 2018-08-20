package com.li.tcc.core.coordinator;

import com.li.tcc.common.bean.entity.TccTransaction;
import com.li.tcc.common.config.TccConfig;

/**
 * this is save transaction log service
 * 
 * @author yuan.li
 */
public interface CoordinatorService {

	/**
	 * init li config
	 *
	 * @param tccConfig
	 * @throws Exception
	 */
	void start(TccConfig tccConfig) throws Exception;

	/**
	 * save tccTransaction
	 *
	 * @param tccTransaction
	 * @return id
	 */
	String save(TccTransaction tccTransaction);

	/**
	 * find by transId
	 *
	 * @param transId
	 * @return TccTransaction
	 */
	TccTransaction findByTransId(String transId);

	/**
	 * remove transaction
	 *
	 * @param id
	 *            transaction pk
	 * @return true success
	 */
	boolean remove(String id);

	/**
	 * update
	 * 
	 * @param tccTransaction
	 */
	void update(TccTransaction tccTransaction);

	/**
	 * update TccTransaction, this is only update Participant field
	 * 
	 * @param tccTransaction
	 * @return rows
	 */
	int updateParticipant(TccTransaction tccTransaction);

	/**
	 * update TccTransaction status
	 * 
	 * @param id
	 *            pk
	 * @param status
	 *            TccActionEnum
	 * @return rows
	 */
	int updateStatus(String id, Integer status);

}
