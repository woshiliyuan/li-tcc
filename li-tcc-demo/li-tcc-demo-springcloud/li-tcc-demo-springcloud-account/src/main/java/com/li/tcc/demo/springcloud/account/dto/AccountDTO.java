package com.li.tcc.demo.springcloud.account.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author yuan.li
 */
public class AccountDTO implements Serializable {

	private static final long serialVersionUID = 7223470850578998427L;
	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * 扣款金额
	 */
	private BigDecimal amount;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
