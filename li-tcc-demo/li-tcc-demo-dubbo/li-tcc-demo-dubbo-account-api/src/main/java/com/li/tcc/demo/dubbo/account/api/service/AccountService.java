package com.li.tcc.demo.dubbo.account.api.service;

import com.li.tcc.common.annotation.Tcc;
import com.li.tcc.demo.dubbo.account.api.dto.AccountDTO;
import com.li.tcc.demo.dubbo.account.api.dto.AccountNestedDTO;
import com.li.tcc.demo.dubbo.account.api.entity.AccountDO;

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
	 * 扣款支付
	 *
	 * @param accountNestedDTO
	 *            参数dto
	 * @return true
	 */
	@Tcc
	boolean paymentWithNested(AccountNestedDTO accountNestedDTO);

	/**
	 * 获取用户账户信息
	 * 
	 * @param userId
	 *            用户id
	 * @return AccountDO
	 */
	AccountDO findByUserId(String userId);
}
