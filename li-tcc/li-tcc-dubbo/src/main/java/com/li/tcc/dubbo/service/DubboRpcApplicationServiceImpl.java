package com.li.tcc.dubbo.service;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.li.tcc.core.service.RpcApplicationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * DubboRpcApplicationServiceImpl
 * 
 * @author yuan.li
 */
@Service("applicationService")
public class DubboRpcApplicationServiceImpl implements RpcApplicationService {

	/**
	 * dubbo ApplicationConfig
	 */
	private final ApplicationConfig applicationConfig;

	@Autowired(required = false)
	public DubboRpcApplicationServiceImpl(final ApplicationConfig applicationConfig) {
		this.applicationConfig = applicationConfig;
	}

	@Override
	public String getName() {
		return Optional.ofNullable(applicationConfig).orElse(new ApplicationConfig("li-dubbo")).getName();
	}
}
