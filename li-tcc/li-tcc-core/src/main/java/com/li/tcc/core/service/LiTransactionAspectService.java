package com.li.tcc.core.service;

import org.aspectj.lang.ProceedingJoinPoint;

import com.li.tcc.common.bean.context.TccTransactionContext;

/**
 * @author yuan.li
 */
@FunctionalInterface
public interface LiTransactionAspectService {

	/**
	 * tcc 事务切面服务
	 *
	 * @param tccTransactionContext
	 *            tcc事务上下文对象
	 * @param point
	 *            切点
	 * @return object
	 * @throws Throwable
	 */
	Object invoke(TccTransactionContext tccTransactionContext, ProceedingJoinPoint point) throws Throwable;
}
