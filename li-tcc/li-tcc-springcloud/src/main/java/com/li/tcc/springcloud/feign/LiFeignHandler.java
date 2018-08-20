package com.li.tcc.springcloud.feign;

import feign.InvocationHandlerFactory.MethodHandler;

import org.apache.commons.lang3.StringUtils;

import com.li.tcc.common.annotation.Tcc;
import com.li.tcc.common.bean.context.TccTransactionContext;
import com.li.tcc.common.bean.entity.Participant;
import com.li.tcc.common.bean.entity.TccInvocation;
import com.li.tcc.common.enums.TccActionEnum;
import com.li.tcc.common.enums.TccRoleEnum;
import com.li.tcc.core.concurrent.threadlocal.TransactionContextLocal;
import com.li.tcc.core.helper.SpringBeanUtils;
import com.li.tcc.core.service.executor.LiTransactionExecutor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

/**
 * LiFeignHandler
 *
 * @author yuan.li
 */
public class LiFeignHandler implements InvocationHandler {

	private Map<Method, MethodHandler> handlers;

	@Override
	public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
		if (Object.class.equals(method.getDeclaringClass())) {
			return method.invoke(this, args);
		} else {
			final Tcc tcc = method.getAnnotation(Tcc.class);
			if (Objects.isNull(tcc)) {
				return this.handlers.get(method).invoke(args);
			}
			try {
				final TccTransactionContext tccTransactionContext = TransactionContextLocal.getInstance().get();
				final LiTransactionExecutor liTransactionExecutor = SpringBeanUtils.getInstance().getBean(
						LiTransactionExecutor.class);
				final Object invoke = this.handlers.get(method).invoke(args);
				final Participant participant = buildParticipant(tcc, method, args, tccTransactionContext);
				if (tccTransactionContext.getRole() == TccRoleEnum.PROVIDER.getCode()) {
					liTransactionExecutor.registerByNested(tccTransactionContext.getTransId(), participant);
				} else {
					liTransactionExecutor.enlistParticipant(participant);
				}
				return invoke;
			} catch (Throwable throwable) {
				throwable.printStackTrace();
				throw throwable;
			}
		}
	}

	private Participant buildParticipant(final Tcc tcc, final Method method, final Object[] args,
			final TccTransactionContext tccTransactionContext) {
		if (Objects.isNull(tccTransactionContext)
				|| (TccActionEnum.TRYING.getCode() != tccTransactionContext.getAction())) {
			return null;
		}
		// 获取协调方法
		String confirmMethodName = tcc.confirmMethod();
		if (StringUtils.isBlank(confirmMethodName)) {
			confirmMethodName = method.getName();
		}
		String cancelMethodName = tcc.cancelMethod();
		if (StringUtils.isBlank(cancelMethodName)) {
			cancelMethodName = method.getName();
		}
		final Class<?> declaringClass = method.getDeclaringClass();
		TccInvocation confirmInvocation = new TccInvocation(declaringClass, confirmMethodName,
				method.getParameterTypes(), args);
		TccInvocation cancelInvocation = new TccInvocation(declaringClass, cancelMethodName,
				method.getParameterTypes(), args);
		// 封装调用点
		return new Participant(tccTransactionContext.getTransId(), confirmInvocation, cancelInvocation);
	}

	/**
	 * set handlers
	 * 
	 * @param handlers
	 */
	public void setHandlers(final Map<Method, MethodHandler> handlers) {
		this.handlers = handlers;
	}

}
