package com.li.tcc.demo.springcloud.order.client;

import com.li.tcc.common.annotation.Tcc;
import com.li.tcc.demo.springcloud.order.configuration.MyConfiguration;
import com.li.tcc.demo.springcloud.order.dto.InventoryDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yuan.li
 */
@FeignClient(value = "inventory-service", configuration = MyConfiguration.class)
public interface InventoryClient {

	/**
	 * 库存扣减
	 *
	 * @param inventoryDTO
	 *            实体对象
	 * @return true 成功
	 */
	@Tcc
	@RequestMapping("/inventory-service/inventory/decrease")
	Boolean decrease(@RequestBody InventoryDTO inventoryDTO);

	/**
	 * 获取商品库存
	 *
	 * @param productId
	 *            商品id
	 * @return InventoryDO
	 */
	@RequestMapping("/inventory-service/inventory/findByProductId")
	Integer findByProductId(@RequestParam("productId") String productId);

	/**
	 * 模拟库存扣减异常
	 *
	 * @param inventoryDTO
	 *            实体对象
	 * @return true 成功
	 */
	@Tcc
	@RequestMapping("/inventory-service/inventory/mockWithTryException")
	Boolean mockWithTryException(@RequestBody InventoryDTO inventoryDTO);

	/**
	 * 模拟库存扣减超时
	 *
	 * @param inventoryDTO
	 *            实体对象
	 * @return true 成功
	 */
	@Tcc
	@RequestMapping("/inventory-service/inventory/mockWithTryTimeout")
	Boolean mockWithTryTimeout(@RequestBody InventoryDTO inventoryDTO);
}
