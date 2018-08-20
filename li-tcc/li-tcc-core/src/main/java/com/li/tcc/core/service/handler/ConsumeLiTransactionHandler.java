package com.li.tcc.core.service.handler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import com.li.tcc.common.bean.context.TccTransactionContext;
import com.li.tcc.core.service.LiTransactionHandler;

/**
 * ConsumeLiTransactionHandler
 * 
 * @author yuan.li
 */
@Component
public class ConsumeLiTransactionHandler implements LiTransactionHandler {

	@Override
	public Object handler(final ProceedingJoinPoint point, final TccTransactionContext context) throws Throwable {
		return point.proceed();
	}
}
