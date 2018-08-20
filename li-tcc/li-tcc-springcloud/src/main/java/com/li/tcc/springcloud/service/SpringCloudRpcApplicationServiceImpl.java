package com.li.tcc.springcloud.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.li.tcc.core.service.RpcApplicationService;

/**
 * SpringCloudRpcApplicationServiceImpl
 * 
 * @author yuan.li
 */
@Service("applicationService")
public class SpringCloudRpcApplicationServiceImpl implements RpcApplicationService {

	@Value("${spring.application.name}")
	private String appName;

	@Override
	public String getName() {
		return appName;
	}
}