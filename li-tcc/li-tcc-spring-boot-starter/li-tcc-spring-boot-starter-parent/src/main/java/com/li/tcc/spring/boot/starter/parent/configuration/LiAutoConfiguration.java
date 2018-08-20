package com.li.tcc.spring.boot.starter.parent.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.li.tcc.core.bootstrap.LiTransactionBootstrap;
import com.li.tcc.core.service.LiInitService;
import com.li.tcc.spring.boot.starter.parent.config.TccConfigProperties;

/**
 * LiAutoConfiguration is spring boot starter handler
 * 
 * @author yuan.li
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableConfigurationProperties
@ComponentScan(basePackages = { "com.li.tcc" })
public class LiAutoConfiguration {

	private final TccConfigProperties tccConfigProperties;

	@Autowired
	public LiAutoConfiguration(TccConfigProperties tccConfigProperties) {
		this.tccConfigProperties = tccConfigProperties;
	}

	@Bean
	public LiTransactionBootstrap tccTransactionBootstrap(LiInitService liInitService) {
		final LiTransactionBootstrap liTransactionBootstrap = new LiTransactionBootstrap(liInitService);
		liTransactionBootstrap.setBufferSize(tccConfigProperties.getBufferSize());
		liTransactionBootstrap.setRetryMax(tccConfigProperties.getRetryMax());
		liTransactionBootstrap.setRecoverDelayTime(tccConfigProperties.getRecoverDelayTime());
		liTransactionBootstrap.setRepositorySuffix(tccConfigProperties.getRepositorySuffix());
		liTransactionBootstrap.setRepositorySupport(tccConfigProperties.getRepositorySupport());
		liTransactionBootstrap.setScheduledDelay(tccConfigProperties.getScheduledDelay());
		liTransactionBootstrap.setScheduledThreadMax(tccConfigProperties.getScheduledThreadMax());
		liTransactionBootstrap.setSerializer(tccConfigProperties.getSerializer());
		liTransactionBootstrap.setTccFileConfig(tccConfigProperties.getTccFileConfig());
		liTransactionBootstrap.setTccDbConfig(tccConfigProperties.getTccDbConfig());
		liTransactionBootstrap.setTccRedisConfig(tccConfigProperties.getTccRedisConfig());
		liTransactionBootstrap.setTccZookeeperConfig(tccConfigProperties.getTccZookeeperConfig());
		liTransactionBootstrap.setTccMongoConfig(tccConfigProperties.getTccMongoConfig());
		return liTransactionBootstrap;
	}
}
