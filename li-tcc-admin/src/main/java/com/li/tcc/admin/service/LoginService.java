package com.li.tcc.admin.service;

/**
 * LoginService
 * 
 * @author yuan.li
 */
public interface LoginService {

	/**
	 * login
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	Boolean login(String userName, String password);

	/**
	 * logout
	 * 
	 * @return
	 */
	Boolean logout();
}
