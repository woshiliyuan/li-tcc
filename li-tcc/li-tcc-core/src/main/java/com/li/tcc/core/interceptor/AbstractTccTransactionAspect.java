package com.li.tcc.core.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * this is Tcc aspect handler
 * 
 * @author yuan.li
 */
@Aspect
public abstract class AbstractTccTransactionAspect {

	private TccTransactionInterceptor tccTransactionInterceptor;

	protected void setTccTransactionInterceptor(final TccTransactionInterceptor tccTransactionInterceptor) {
		this.tccTransactionInterceptor = tccTransactionInterceptor;
	}

	/**
	 * this is point cut with Tcc
	 */
	@Pointcut("@annotation(com.li.tcc.common.annotation.Tcc)")
	public void liTccInterceptor() {
	}

	/**
	 * this is around in Tcc
	 * 
	 * @param proceedingJoinPoint
	 * @return Object
	 * @throws Throwable
	 */
	@Around("liTccInterceptor()")
	public Object interceptTccMethod(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		return tccTransactionInterceptor.interceptor(proceedingJoinPoint);
	}

	/**
	 * spring Order
	 *
	 * @return int
	 */
	public abstract int getOrder();
}
