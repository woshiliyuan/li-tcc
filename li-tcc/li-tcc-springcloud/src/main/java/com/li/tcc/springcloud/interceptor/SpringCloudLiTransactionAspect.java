package com.li.tcc.springcloud.interceptor;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.li.tcc.core.interceptor.AbstractTccTransactionAspect;

/**
 * SpringCloudLiTransactionAspect
 * 
 * @author yuan.li
 */
@Aspect
@Component
public class SpringCloudLiTransactionAspect extends AbstractTccTransactionAspect implements Ordered {

	@Autowired
	public SpringCloudLiTransactionAspect(final SpringCloudLiTransactionInterceptor springCloudLiTransactionInterceptor) {
		this.setTccTransactionInterceptor(springCloudLiTransactionInterceptor);
	}

	@Override
	public int getOrder() {
		return Ordered.HIGHEST_PRECEDENCE;
	}
}
