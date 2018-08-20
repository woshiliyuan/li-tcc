package com.li.tcc.demo.springcloud.account.service.impl;

import com.li.tcc.common.annotation.Tcc;
import com.li.tcc.common.exception.TccRuntimeException;
import com.li.tcc.demo.springcloud.account.dto.AccountDTO;
import com.li.tcc.demo.springcloud.account.entity.AccountDO;
import com.li.tcc.demo.springcloud.account.mapper.AccountMapper;
import com.li.tcc.demo.springcloud.account.service.AccountService;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author yuan.li
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

	/**
	 * logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

	private final AccountMapper accountMapper;

	@Autowired(required = false)
	public AccountServiceImpl(AccountMapper accountMapper) {
		this.accountMapper = accountMapper;
	}

	/**
	 * 扣款支付
	 *
	 * @param accountDTO
	 *            参数dto
	 * @return true
	 */
	@Override
	@Tcc(confirmMethod = "confirm", cancelMethod = "cancel")
	@Transactional
	public boolean payment(AccountDTO accountDTO) {
		LOGGER.debug("============springcloud执行try付款接口===============");
		final AccountDO accountDO = accountMapper.findByUserId(accountDTO.getUserId());
		accountDO.setBalance(accountDO.getBalance().subtract(accountDTO.getAmount()));
		accountDO.setFreezeAmount(accountDO.getFreezeAmount().add(accountDTO.getAmount()));
		accountDO.setUpdateTime(new Date());

		final int update = accountMapper.update(accountDO);
		if (update != 1) {
			throw new TccRuntimeException("资金不足！");
		}
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// throw new RuntimeException("");
		return Boolean.TRUE;
	}

	/**
	 * 获取用户账户信息
	 *
	 * @param userId
	 *            用户id
	 * @return AccountDO
	 */
	@Override
	public AccountDO findByUserId(String userId) {
		return accountMapper.findByUserId(userId);
	}

	public boolean confirm(AccountDTO accountDTO) {

		LOGGER.debug("============springcloud tcc 执行确认付款接口===============");

		final AccountDO accountDO = accountMapper.findByUserId(accountDTO.getUserId());
		accountDO.setFreezeAmount(accountDO.getFreezeAmount().subtract(accountDTO.getAmount()));
		accountDO.setUpdateTime(new Date());
		final int rows = accountMapper.confirm(accountDO);
		if (rows != 1) {
			throw new TccRuntimeException("确认扣减账户异常！");
		}
		return Boolean.TRUE;
	}

	public boolean cancel(AccountDTO accountDTO) {

		LOGGER.debug("============springcloud tcc 执行取消付款接口===============");
		final AccountDO accountDO = accountMapper.findByUserId(accountDTO.getUserId());
		accountDO.setBalance(accountDO.getBalance().add(accountDTO.getAmount()));
		accountDO.setFreezeAmount(accountDO.getFreezeAmount().subtract(accountDTO.getAmount()));
		accountDO.setUpdateTime(new Date());
		final int rows = accountMapper.cancel(accountDO);
		if (rows != 1) {
			throw new TccRuntimeException("取消扣减账户异常！");
		}
		return Boolean.TRUE;
	}
}
