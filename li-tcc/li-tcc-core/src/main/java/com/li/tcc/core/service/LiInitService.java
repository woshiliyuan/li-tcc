package com.li.tcc.core.service;

import com.li.tcc.common.config.TccConfig;

/**
 * @author yuan.li
 */
@FunctionalInterface
public interface LiInitService {

	/**
	 * tcc分布式事务初始化方法
	 *
	 * @param tccConfig
	 *            TCC配置
	 */
	void initialization(TccConfig tccConfig);
}
