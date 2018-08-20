package com.li.tcc.admin.service.compensate;

import com.google.common.base.Splitter;
import com.li.tcc.admin.service.ApplicationNameService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ApplicationNameServiceImpl
 * 
 * @author yuan.li
 */
@Service("recoverApplicationNameService")
public class ApplicationNameServiceImpl implements ApplicationNameService {

	@Value("${compensation.application.list}")
	private String appNameList;

	@Override
	public List<String> list() {
		return Splitter.on(",").splitToList(appNameList);
	}
}
