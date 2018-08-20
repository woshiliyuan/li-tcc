package com.li.tcc.demo.springcloud.account.service;

import com.li.tcc.common.annotation.Tcc;
import com.li.tcc.demo.springcloud.account.dto.AccountDTO;
import com.li.tcc.demo.springcloud.account.entity.AccountDO;

/**
 * @author yuan.li
 */
public interface AccountService {

	/**
	 * 扣款支付
	 *
	 * @param accountDTO
	 *            参数dto
	 * @return true
	 */
	@Tcc
	boolean payment(AccountDTO accountDTO);

	/**
	 * 获取用户账户信息
	 * 
	 * @param userId
	 *            用户id
	 * @return AccountDO
	 */
	AccountDO findByUserId(String userId);
}
