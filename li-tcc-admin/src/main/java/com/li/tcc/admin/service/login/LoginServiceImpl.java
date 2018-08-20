package com.li.tcc.admin.service.login;

import com.li.tcc.common.utils.LogUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.li.tcc.admin.service.LoginService;

/**
 * LoginServiceImpl
 * 
 * @author yuan.li
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

	public static boolean LOGIN_SUCCESS = false;

	/**
	 * logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Value("${tcc.admin.userName}")
	private String userName;

	@Value("${tcc.admin.password}")
	private String password;

	@Override
	public Boolean login(final String userName, final String password) {
		LogUtil.info(LOGGER, "输入的用户名密码为:{}", () -> userName + "," + password);
		if (userName.equals(this.userName) && password.equals(this.password)) {
			LOGIN_SUCCESS = true;
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	public Boolean logout() {
		LOGIN_SUCCESS = false;
		return Boolean.TRUE;
	}
}
