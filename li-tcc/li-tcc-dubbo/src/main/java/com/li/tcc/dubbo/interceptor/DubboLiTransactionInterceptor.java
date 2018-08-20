package com.li.tcc.dubbo.interceptor;

import com.alibaba.dubbo.rpc.RpcContext;
import com.li.tcc.common.bean.context.TccTransactionContext;
import com.li.tcc.common.constant.CommonConstant;
import com.li.tcc.common.utils.GsonUtils;
import com.li.tcc.core.concurrent.threadlocal.TransactionContextLocal;
import com.li.tcc.core.interceptor.TccTransactionInterceptor;
import com.li.tcc.core.service.LiTransactionAspectService;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * DubboLiTransactionInterceptor
 * 
 * @author yuan.li
 */
@Component
public class DubboLiTransactionInterceptor implements TccTransactionInterceptor {

	private final LiTransactionAspectService liTransactionAspectService;

	@Autowired
	public DubboLiTransactionInterceptor(final LiTransactionAspectService liTransactionAspectService) {
		this.liTransactionAspectService = liTransactionAspectService;
	}

	@Override
	public Object interceptor(final ProceedingJoinPoint pjp) throws Throwable {
		final String context = RpcContext.getContext().getAttachment(CommonConstant.TCC_TRANSACTION_CONTEXT);
		TccTransactionContext tccTransactionContext;
		if (StringUtils.isNoneBlank(context)) {
			tccTransactionContext = GsonUtils.getInstance().fromJson(context, TccTransactionContext.class);
		} else {
			tccTransactionContext = TransactionContextLocal.getInstance().get();
		}
		return liTransactionAspectService.invoke(tccTransactionContext, pjp);
	}
}
