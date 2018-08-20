package com.li.tcc.core.service;

import org.aspectj.lang.ProceedingJoinPoint;

import com.li.tcc.common.bean.context.TccTransactionContext;

/**
 * TccTransactionHandler
 * 
 * @author yuan.li
 */
@FunctionalInterface
public interface LiTransactionHandler {

	/**
	 * aop handler
	 *
	 * @param point
	 *            point
	 * @param tccTransactionContext
	 *            transaction context
	 * @return Object
	 * @throws Throwable
	 */
	Object handler(ProceedingJoinPoint point, TccTransactionContext tccTransactionContext) throws Throwable;
}
