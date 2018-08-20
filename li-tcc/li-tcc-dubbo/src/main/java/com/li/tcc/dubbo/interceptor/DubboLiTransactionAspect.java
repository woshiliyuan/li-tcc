package com.li.tcc.dubbo.interceptor;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.li.tcc.core.interceptor.AbstractTccTransactionAspect;

/**
 * dubbo impl aspect
 * 
 * @author yuan.li
 */
@Aspect
@Component
public class DubboLiTransactionAspect extends AbstractTccTransactionAspect implements Ordered {

	@Autowired
	public DubboLiTransactionAspect(final DubboLiTransactionInterceptor dubboLiTransactionInterceptor) {
		super.setTccTransactionInterceptor(dubboLiTransactionInterceptor);
	}

	@Override
	public int getOrder() {
		return Ordered.HIGHEST_PRECEDENCE;
	}
}
