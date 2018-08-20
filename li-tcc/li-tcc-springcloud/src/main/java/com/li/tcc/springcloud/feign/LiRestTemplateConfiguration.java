package com.li.tcc.springcloud.feign;

import feign.Feign;
import feign.InvocationHandlerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * LiRestTemplateConfiguration
 * 
 * @author yuan.li
 */
@Configuration
public class LiRestTemplateConfiguration {

	/**
	 * build feign
	 *
	 * @return Feign.Builder
	 */
	@Bean
	@Scope("prototype")
	public Feign.Builder feignBuilder() {
		return Feign.builder().requestInterceptor(new LiRestTemplateInterceptor())
				.invocationHandlerFactory(invocationHandlerFactory());
	}

	/**
	 * build InvocationHandlerFactory
	 * 
	 * @return InvocationHandlerFactory
	 */
	@Bean
	public InvocationHandlerFactory invocationHandlerFactory() {
		return (target, dispatch) -> {
			LiFeignHandler handler = new LiFeignHandler();
			handler.setHandlers(dispatch);
			return handler;
		};
	}

}
