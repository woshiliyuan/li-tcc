package com.li.tcc.core.helper;

import org.springframework.context.ConfigurableApplicationContext;

import com.li.tcc.common.utils.AssertUtils;

/**
 * SpringBeanUtils
 * 
 * @author yuan.li
 */
public final class SpringBeanUtils {

	private static final SpringBeanUtils INSTANCE = new SpringBeanUtils();

	private ConfigurableApplicationContext cfgContext;

	private SpringBeanUtils() {
		if (INSTANCE != null) {
			throw new Error("error");
		}
	}

	/**
	 * get SpringBeanUtils
	 * 
	 * @return SpringBeanUtils
	 */
	public static SpringBeanUtils getInstance() {
		return INSTANCE;
	}

	/**
	 * get spring bean
	 *
	 * @param type
	 *            type
	 * @param <T>
	 *            class
	 * @return bean
	 */
	public <T> T getBean(final Class<T> type) {
		AssertUtils.notNull(type);
		return cfgContext.getBean(type);
	}

	/**
	 * register bean in spring ioc
	 * 
	 * @param beanName
	 * @param obj
	 *            bean
	 */
	public void registerBean(final String beanName, final Object obj) {
		AssertUtils.notNull(beanName);
		AssertUtils.notNull(obj);
		cfgContext.getBeanFactory().registerSingleton(beanName, obj);
	}

	/**
	 * set application context
	 * 
	 * @param cfgContext
	 *            application context
	 */
	public void setCfgContext(final ConfigurableApplicationContext cfgContext) {
		this.cfgContext = cfgContext;
	}
}
