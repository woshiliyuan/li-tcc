package com.li.tcc.core.service;

import com.li.tcc.common.bean.context.TccTransactionContext;

/**
 * @author yuan.li
 */
@FunctionalInterface
public interface LiTransactionFactoryService<T> {

	/**
	 * 返回 实现TxTransactionHandler类的名称
	 *
	 * @param context
	 * @return Class<T>
	 * @throws Throwable
	 */
	Class<T> factoryOf(TccTransactionContext context) throws Throwable;
}
