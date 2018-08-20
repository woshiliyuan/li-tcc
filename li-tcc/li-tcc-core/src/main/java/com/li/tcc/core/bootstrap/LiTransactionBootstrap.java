package com.li.tcc.core.bootstrap;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

import com.li.tcc.common.config.TccConfig;
import com.li.tcc.core.helper.SpringBeanUtils;
import com.li.tcc.core.service.LiInitService;

/**
 * TransactionBootstrap
 * 
 * @author yuan.li
 */
public class LiTransactionBootstrap extends TccConfig implements ApplicationContextAware {

	private final LiInitService liInitService;

	@Autowired
	public LiTransactionBootstrap(final LiInitService liInitService) {
		this.liInitService = liInitService;
	}

	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
		SpringBeanUtils.getInstance().setCfgContext((ConfigurableApplicationContext) applicationContext);
		start(this);
	}

	private void start(final TccConfig tccConfig) {
		liInitService.initialization(tccConfig);
	}
}
