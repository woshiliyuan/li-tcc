package com.li.tcc.demo.dubbo.inventory.api.dto;

import java.io.Serializable;

/**
 * @author yuan.li
 */
public class InventoryDTO implements Serializable {

	private static final long serialVersionUID = 8229355519336565493L;

	/**
	 * 商品id
	 */
	private String productId;

	/**
	 * 数量
	 */
	private Integer count;

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
