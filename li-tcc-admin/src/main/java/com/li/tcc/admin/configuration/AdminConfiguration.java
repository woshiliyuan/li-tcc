package com.li.tcc.admin.configuration;

import com.li.tcc.common.enums.SerializeEnum;
import com.li.tcc.common.serializer.KryoSerializer;
import com.li.tcc.common.serializer.ObjectSerializer;
import com.li.tcc.common.utils.ServiceBootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.li.tcc.admin.interceptor.AuthInterceptor;

import java.util.Objects;
import java.util.ServiceLoader;
import java.util.stream.StreamSupport;

/**
 * AdminConfiguration
 * 
 * @author yuan.li
 */
@Configuration
public class AdminConfiguration {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addInterceptors(final InterceptorRegistry registry) {
				registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**");
			}
		};
	}

	@Configuration
	static class SerializerConfiguration {

		private final Environment env;

		@Autowired
		SerializerConfiguration(final Environment env) {
			this.env = env;
		}

		@Bean
		public ObjectSerializer objectSerializer() {
			final SerializeEnum serializeEnum = SerializeEnum.getEnum(env.getProperty("recover.serializer.support"));
			final ServiceLoader<ObjectSerializer> objectSerializers = ServiceBootstrap.loadAll(ObjectSerializer.class);
			return StreamSupport
					.stream(objectSerializers.spliterator(), false)
					.filter(objectSerializer -> Objects.equals(objectSerializer.getScheme(),
							serializeEnum.getSerialize())).findFirst().orElse(new KryoSerializer());
		}
	}
}
