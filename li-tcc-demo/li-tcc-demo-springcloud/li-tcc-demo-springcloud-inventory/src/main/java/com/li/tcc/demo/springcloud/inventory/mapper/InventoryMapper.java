package com.li.tcc.demo.springcloud.inventory.mapper;

import com.li.tcc.demo.springcloud.inventory.entity.InventoryDO;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author yuan.li
 */
public interface InventoryMapper {

	/**
	 * 库存扣减
	 *
	 * @param inventory
	 *            实体对象
	 * @return rows
	 */
	@Update("update inventory set total_inventory =#{totalInventory}," + " lock_inventory= #{lockInventory} "
			+ " where product_id =#{productId}  and  total_inventory >0  ")
	int decrease(InventoryDO inventory);

	/**
	 * 库存扣减confirm
	 *
	 * @param inventory
	 *            实体对象
	 * @return rows
	 */
	@Update("update inventory set " + " lock_inventory= #{lockInventory} "
			+ " where product_id =#{productId}  and lock_inventory >0 ")
	int confirm(InventoryDO inventory);

	/**
	 * 库存扣减 cancel
	 *
	 * @param inventory
	 *            实体对象
	 * @return rows
	 */
	@Update("update inventory set total_inventory =#{totalInventory}," + " lock_inventory= #{lockInventory} "
			+ " where product_id =#{productId}  and lock_inventory >0 ")
	int cancel(InventoryDO inventory);

	/**
	 * 根据商品id找到库存信息
	 *
	 * @param productId
	 *            商品id
	 * @return Inventory
	 */
	@Select("select * from inventory where product_id =#{productId}")
	InventoryDO findByProductId(String productId);
}
