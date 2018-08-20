package com.li.tcc.dubbo.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.li.tcc.common.annotation.Tcc;
import com.li.tcc.common.bean.context.TccTransactionContext;
import com.li.tcc.common.bean.entity.Participant;
import com.li.tcc.common.bean.entity.TccInvocation;
import com.li.tcc.common.constant.CommonConstant;
import com.li.tcc.common.enums.TccActionEnum;
import com.li.tcc.common.enums.TccRoleEnum;
import com.li.tcc.common.exception.TccRuntimeException;
import com.li.tcc.common.utils.GsonUtils;
import com.li.tcc.core.concurrent.threadlocal.TransactionContextLocal;
import com.li.tcc.core.service.executor.LiTransactionExecutor;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * impl dubbo filter
 * 
 * @author yuan.li
 */
@Activate(group = { Constants.SERVER_KEY, Constants.CONSUMER })
public class LiTransactionFilter implements Filter {

	private LiTransactionExecutor liTransactionExecutor;

	/**
	 * this is init by dubbo spi set liTransactionExecutor
	 *
	 * @param liTransactionExecutor
	 *            LiTransactionExecutor
	 */
	public void setLiTransactionExecutor(final LiTransactionExecutor liTransactionExecutor) {
		this.liTransactionExecutor = liTransactionExecutor;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Result invoke(final Invoker<?> invoker, final Invocation invocation) throws RpcException {
		String methodName = invocation.getMethodName();
		Class clazz = invoker.getInterface();
		Class[] args = invocation.getParameterTypes();
		final Object[] arguments = invocation.getArguments();
		Method method = null;
		Tcc tcc = null;
		try {
			method = clazz.getDeclaredMethod(methodName, args);
			tcc = method.getAnnotation(Tcc.class);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		if (Objects.nonNull(tcc)) {
			try {
				final TccTransactionContext tccTransactionContext = TransactionContextLocal.getInstance().get();
				if (Objects.nonNull(tccTransactionContext)) {
					RpcContext.getContext().setAttachment(CommonConstant.TCC_TRANSACTION_CONTEXT,
							GsonUtils.getInstance().toJson(tccTransactionContext));
				}
				final Result result = invoker.invoke(invocation);
				// 如果result 没有异常就保存
				if (!result.hasException()) {
					final Participant participant = buildParticipant(tccTransactionContext, tcc, method, clazz,
							arguments, args);
					if (tccTransactionContext.getRole() == TccRoleEnum.PROVIDER.getCode()) {
						liTransactionExecutor.registerByNested(tccTransactionContext.getTransId(), participant);
					} else {
						liTransactionExecutor.enlistParticipant(participant);
					}
				}
				return result;
			} catch (RpcException e) {
				e.printStackTrace();
				throw e;
			}
		} else {
			return invoker.invoke(invocation);
		}
	}

	@SuppressWarnings("rawtypes")
	private Participant buildParticipant(final TccTransactionContext tccTransactionContext, final Tcc tcc,
			final Method method, final Class clazz, final Object[] arguments, final Class... args)
			throws TccRuntimeException {

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
		TccInvocation confirmInvocation = new TccInvocation(clazz, confirmMethodName, args, arguments);
		TccInvocation cancelInvocation = new TccInvocation(clazz, cancelMethodName, args, arguments);
		// 封装调用点
		return new Participant(tccTransactionContext.getTransId(), confirmInvocation, cancelInvocation);
	}
}
