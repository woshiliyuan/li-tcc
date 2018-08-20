package com.li.tcc.demo.springcloud.order.client;

import com.li.tcc.common.annotation.Tcc;
import com.li.tcc.demo.springcloud.order.configuration.MyConfiguration;
import com.li.tcc.demo.springcloud.order.dto.AccountDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author yuan.li
 */
@FeignClient(value = "account-service", configuration = MyConfiguration.class)
public interface AccountClient {

	/**
	 * 用户账户付款
	 *
	 * @param accountDO
	 *            实体类
	 * @return true 成功
	 */
	@PostMapping("/account-service/account/payment")
	@Tcc
	Boolean payment(@RequestBody AccountDTO accountDO);

	/**
	 * 获取用户账户信息
	 *
	 * @param userId
	 *            用户id
	 * @return AccountDO
	 */
	@PostMapping("/account-service/account/findByUserId")
	BigDecimal findByUserId(@RequestParam("userId") String userId);

}
