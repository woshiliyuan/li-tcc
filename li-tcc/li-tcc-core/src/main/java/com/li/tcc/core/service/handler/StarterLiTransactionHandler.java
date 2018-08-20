package com.li.tcc.core.service.handler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.li.tcc.common.bean.context.TccTransactionContext;
import com.li.tcc.common.bean.entity.TccTransaction;
import com.li.tcc.common.enums.TccActionEnum;
import com.li.tcc.core.concurrent.threadlocal.TransactionContextLocal;
import com.li.tcc.core.service.LiTransactionHandler;
import com.li.tcc.core.service.executor.LiTransactionExecutor;

/**
 * this is transaction starter
 *
 * @author yuan.li
 */
@Component
public class StarterLiTransactionHandler implements LiTransactionHandler {

	private final LiTransactionExecutor liTransactionExecutor;

	@Autowired
	public StarterLiTransactionHandler(final LiTransactionExecutor liTransactionExecutor) {
		this.liTransactionExecutor = liTransactionExecutor;
	}

	@Override
	public Object handler(final ProceedingJoinPoint point, TccTransactionContext context) throws Throwable {
		Object returnValue = null;
		try {
			TccTransaction tccTransaction;
			context = TransactionContextLocal.getInstance().get();
			if (context == null) {
				tccTransaction = liTransactionExecutor.begin(point);
				try {
					// execute try
					returnValue = point.proceed();
					tccTransaction.setStatus(TccActionEnum.TRYING.getCode());
					liTransactionExecutor.updateStatus(tccTransaction);
				} catch (Throwable throwable) {
					// if exception ,execute cancel
					liTransactionExecutor.cancel(liTransactionExecutor.getCurrentTransaction());
					throw throwable;
				}
				// execute confirm
				liTransactionExecutor.confirm(liTransactionExecutor.getCurrentTransaction());
			} else if (context.getAction() == TccActionEnum.CONFIRMING.getCode()) {
				// execute confirm
				liTransactionExecutor.confirm(liTransactionExecutor.getCurrentTransaction());
			}

		} finally {
			liTransactionExecutor.remove();
		}
		return returnValue;
	}
}
