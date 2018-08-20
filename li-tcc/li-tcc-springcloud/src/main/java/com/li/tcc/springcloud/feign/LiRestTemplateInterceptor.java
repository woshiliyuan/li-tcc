package com.li.tcc.springcloud.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import org.springframework.context.annotation.Configuration;

import com.li.tcc.common.bean.context.TccTransactionContext;
import com.li.tcc.common.constant.CommonConstant;
import com.li.tcc.common.utils.GsonUtils;
import com.li.tcc.core.concurrent.threadlocal.TransactionContextLocal;

/**
 * LiRestTemplateInterceptor
 * 
 * @author yuan.li
 */
@Configuration
public class LiRestTemplateInterceptor implements RequestInterceptor {

	@Override
	public void apply(final RequestTemplate requestTemplate) {
		final TccTransactionContext tccTransactionContext = TransactionContextLocal.getInstance().get();
		requestTemplate.header(CommonConstant.TCC_TRANSACTION_CONTEXT,
				GsonUtils.getInstance().toJson(tccTransactionContext));
	}
}