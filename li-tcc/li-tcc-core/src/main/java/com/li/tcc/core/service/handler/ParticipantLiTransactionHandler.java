package com.li.tcc.core.service.handler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.li.tcc.common.bean.context.TccTransactionContext;
import com.li.tcc.common.bean.entity.TccTransaction;
import com.li.tcc.common.enums.TccActionEnum;
import com.li.tcc.core.cache.TccTransactionCacheManager;
import com.li.tcc.core.service.LiTransactionHandler;
import com.li.tcc.core.service.executor.LiTransactionExecutor;

import java.lang.reflect.Method;

/**
 * Participant Handler
 *
 * @author yuan.li
 */
@Component
public class ParticipantLiTransactionHandler implements LiTransactionHandler {

	private final LiTransactionExecutor liTransactionExecutor;

	@Autowired
	public ParticipantLiTransactionHandler(final LiTransactionExecutor liTransactionExecutor) {
		this.liTransactionExecutor = liTransactionExecutor;
	}

	@Override
	public Object handler(final ProceedingJoinPoint point, final TccTransactionContext context) throws Throwable {
		TccTransaction tccTransaction = null;
		TccTransaction currentTransaction;
		switch (TccActionEnum.getEnum(context.getAction())) {
		case TRYING:
			try {
				tccTransaction = liTransactionExecutor.beginParticipant(context, point);
				final Object proceed = point.proceed();
				tccTransaction.setStatus(TccActionEnum.TRYING.getCode());
				// update log status to try
				liTransactionExecutor.updateStatus(tccTransaction);
				return proceed;
			} catch (Throwable throwable) {
				// if exception ,delete log.
				liTransactionExecutor.deleteTransaction(tccTransaction);
				assert tccTransaction != null;
				TccTransactionCacheManager.getInstance().removeByKey(tccTransaction.getTransId());
				throw throwable;
			}
		case CONFIRMING:
			currentTransaction = TccTransactionCacheManager.getInstance().getTccTransaction(context.getTransId());
			liTransactionExecutor.confirm(currentTransaction);
			break;
		case CANCELING:
			currentTransaction = TccTransactionCacheManager.getInstance().getTccTransaction(context.getTransId());
			liTransactionExecutor.cancel(currentTransaction);
			break;
		default:
			break;
		}
		Method method = ((MethodSignature) (point.getSignature())).getMethod();
		return getDefaultValue(method.getReturnType());
	}

	@SuppressWarnings("rawtypes")
	private Object getDefaultValue(final Class type) {
		if (boolean.class.equals(type)) {
			return false;
		} else if (byte.class.equals(type)) {
			return 0;
		} else if (short.class.equals(type)) {
			return 0;
		} else if (int.class.equals(type)) {
			return 0;
		} else if (long.class.equals(type)) {
			return 0;
		} else if (float.class.equals(type)) {
			return 0;
		} else if (double.class.equals(type)) {
			return 0;
		}
		return null;
	}
}
