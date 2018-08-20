package com.li.tcc.demo.springcloud.order.configuration;

import feign.Feign;
import feign.InvocationHandlerFactory;
import feign.Request;
import feign.Retryer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.li.tcc.springcloud.feign.LiFeignHandler;
import com.li.tcc.springcloud.feign.LiRestTemplateInterceptor;

/**
 * @author yuan.li
 */
@Configuration
public class MyConfiguration {

	@Bean
	@Scope("prototype")
	public Feign.Builder feignBuilder() {
		return Feign.builder().requestInterceptor(new LiRestTemplateInterceptor())
				.invocationHandlerFactory(invocationHandlerFactory());
	}

	@Bean
	public InvocationHandlerFactory invocationHandlerFactory() {
		return (target, dispatch) -> {
			LiFeignHandler handler = new LiFeignHandler();
			// handler.setTarget(target);
			handler.setHandlers(dispatch);
			return handler;
		};
	}

	@Bean
	Request.Options feignOptions() {
		return new Request.Options(5000, 5000);
	}

	@Bean
	Retryer feignRetryer() {
		return Retryer.NEVER_RETRY;
	}
}