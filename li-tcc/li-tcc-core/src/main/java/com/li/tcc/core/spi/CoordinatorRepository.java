package com.li.tcc.core.spi;

import java.util.Date;
import java.util.List;

import com.li.tcc.common.bean.entity.TccTransaction;
import com.li.tcc.common.config.TccConfig;
import com.li.tcc.common.serializer.ObjectSerializer;

/**
 * CoordinatorRepository
 * 
 * @author yuan.li
 */
public interface CoordinatorRepository {

	int ROWS = 1;

	int FAIL_ROWS = 0;

	/**
	 * create TccTransaction
	 *
	 * @param tccTransaction
	 *            TccTransaction
	 * @return rows 1
	 */
	int create(TccTransaction tccTransaction);

	/**
	 * delete TccTransaction
	 *
	 * @param id
	 *            pk
	 * @return rows 1
	 */
	int remove(String id);

	/**
	 * update TccTransaction
	 *
	 * @param tccTransaction
	 *            TccTransaction
	 * @return rows 1 success 0 fail
	 */
	int update(TccTransaction tccTransaction);

	/**
	 * update participants
	 *
	 * @param tccTransaction
	 *            TccTransaction
	 * @return rows 1 success 0 fail
	 */
	int updateParticipant(TccTransaction tccTransaction);

	/**
	 * update status
	 * 
	 * @param id
	 *            pk
	 * @param status
	 *            status
	 * @return rows 1 success 0 fail
	 */
	int updateStatus(String id, Integer status);

	/**
	 * get by id
	 *
	 * @param id
	 *            pk
	 * @return TccTransaction
	 */
	TccTransaction findById(String id);

	/**
	 * list all
	 *
	 * @return TccTransaction
	 */
	List<TccTransaction> listAll();

	/**
	 * 获取延迟多长时间后的事务信息,只要为了防止并发的时候，刚新增的数据被执行
	 *
	 * @param date
	 *            延迟后的时间
	 * @return TccTransaction
	 */
	List<TccTransaction> listAllByDelay(Date date);

	/**
	 * init
	 *
	 * @param modelName
	 *            modelName
	 * @param tccConfig
	 *            TccConfig
	 */
	void init(String modelName, TccConfig tccConfig);

	/**
	 * set scheme
	 *
	 * @return scheme
	 */
	String getScheme();

	/**
	 * set objectSerializer
	 *
	 * @param objectSerializer
	 *            ObjectSerializer
	 */
	void setSerializer(ObjectSerializer objectSerializer);
}
