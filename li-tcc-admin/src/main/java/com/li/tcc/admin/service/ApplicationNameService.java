package com.li.tcc.admin.service;

import java.util.List;

/**
 * ApplicationNameService
 * 
 * @author yuan.li
 */
@FunctionalInterface
public interface ApplicationNameService {

	/**
	 * get app name
	 *
	 * @return app name list
	 */
	List<String> list();
}
