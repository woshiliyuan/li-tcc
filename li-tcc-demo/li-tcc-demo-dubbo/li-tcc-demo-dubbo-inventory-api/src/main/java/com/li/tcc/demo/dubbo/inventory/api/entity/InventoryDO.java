package com.li.tcc.demo.dubbo.inventory.api.entity;

import java.io.Serializable;

/**
 * @author yuan.li
 */
public class InventoryDO implements Serializable {

	private static final long serialVersionUID = 6957734749389133832L;
	private Integer id;

	/**
	 * 商品id
	 */
	private String productId;

	/**
	 * 总库存
	 */
	private Integer totalInventory;

	/**
	 * 锁定库存
	 */
	private Integer lockInventory;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getTotalInventory() {
		return totalInventory;
	}

	public void setTotalInventory(Integer totalInventory) {
		this.totalInventory = totalInventory;
	}

	public Integer getLockInventory() {
		return lockInventory;
	}

	public void setLockInventory(Integer lockInventory) {
		this.lockInventory = lockInventory;
	}

}
