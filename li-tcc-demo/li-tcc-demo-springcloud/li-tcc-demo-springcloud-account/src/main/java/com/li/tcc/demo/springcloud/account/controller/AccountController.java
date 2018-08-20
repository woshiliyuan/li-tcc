package com.li.tcc.demo.springcloud.account.controller;

import com.li.tcc.demo.springcloud.account.dto.AccountDTO;
import com.li.tcc.demo.springcloud.account.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author yuan.li
 */
@RestController
@RequestMapping("/account")
public class AccountController {

	private final AccountService accountService;

	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@RequestMapping("/payment")
	public Boolean save(@RequestBody AccountDTO accountDO) {
		return accountService.payment(accountDO);
	}

	@RequestMapping("/findByUserId")
	public BigDecimal findByUserId(@RequestParam("userId") String userId) {
		return accountService.findByUserId(userId).getBalance();
	}

}
