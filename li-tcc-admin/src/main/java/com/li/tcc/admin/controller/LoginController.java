package com.li.tcc.admin.controller;

import com.li.tcc.common.utils.httpclient.AjaxResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.li.tcc.admin.dto.UserDTO;
import com.li.tcc.admin.service.LoginService;

/**
 * login rest controller
 * 
 * @author yuan.li
 */
@RestController
public class LoginController {

	private final LoginService loginService;

	@Autowired
	public LoginController(final LoginService loginService) {
		this.loginService = loginService;
	}

	@PostMapping("/login")
	public AjaxResponse login(@RequestBody final UserDTO userDTO) {
		final Boolean login = loginService.login(userDTO.getUserName(), userDTO.getPassword());
		return AjaxResponse.success(login);
	}

	@PostMapping("/logout")
	public AjaxResponse logout() {
		return AjaxResponse.success(loginService.logout());
	}

}
