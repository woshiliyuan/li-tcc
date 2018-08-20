package com.li.tcc.demo.dubbo.inventory.api.service;

import com.li.tcc.common.annotation.Tcc;
import com.li.tcc.demo.dubbo.inventory.api.dto.InventoryDTO;
import com.li.tcc.demo.dubbo.inventory.api.entity.InventoryDO;

/**
 * @author yuan.li
 */
public interface InventoryService {

	/**
	 * 扣减库存操作 这一个tcc接口
	 *
	 * @param inventoryDTO
	 *            库存DTO对象
	 * @return true
	 */
	@Tcc
	Boolean decrease(InventoryDTO inventoryDTO);

	/**
	 * 获取商品库存信息
	 * 
	 * @param productId
	 *            商品id
	 * @return InventoryDO
	 */
	InventoryDO findByProductId(String productId);

	/**
	 * mock扣减库存异常
	 *
	 * @param inventoryDTO
	 *            dto对象
	 * @return String
	 */
	@Tcc
	String mockWithTryException(InventoryDTO inventoryDTO);

	/**
	 * mock扣减库存超时
	 *
	 * @param inventoryDTO
	 *            dto对象
	 * @return String
	 */
	@Tcc
	Boolean mockWithTryTimeout(InventoryDTO inventoryDTO);

	/**
	 * mock 扣减库存confirm异常
	 *
	 * @param inventoryDTO
	 *            dto对象
	 * @return String
	 */
	@Tcc
	String mockWithConfirmException(InventoryDTO inventoryDTO);

	/**
	 * mock 扣减库存confirm超时
	 *
	 * @param inventoryDTO
	 *            dto对象
	 * @return True
	 */
	@Tcc
	Boolean mockWithConfirmTimeout(InventoryDTO inventoryDTO);

}
