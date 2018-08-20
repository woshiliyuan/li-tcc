package com.li.tcc.demo.dubbo.account.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author yuan.li
 */
public class AccountNestedDTO implements Serializable {

	private static final long serialVersionUID = 7223470850578998427L;
	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * 扣款金额
	 */
	private BigDecimal amount;

	/**
	 * productId
	 */
	private String productId;

	/**
	 * count
	 */
	private Integer count;

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

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
