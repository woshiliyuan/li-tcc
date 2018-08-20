package com.li.tcc.core.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * TccTransactionInterceptor
 * 
 * @author yuan.li
 */
@FunctionalInterface
public interface TccTransactionInterceptor {

	/**
	 * li interceptor handler
	 *
	 * @param pjp
	 *            tcc point cut
	 * @return Object
	 * @throws Throwable
	 */
	Object interceptor(ProceedingJoinPoint pjp) throws Throwable;
}
