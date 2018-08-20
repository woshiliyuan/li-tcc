package com.li.tcc.demo.springcloud.inventory.controller;

import com.li.tcc.demo.springcloud.inventory.dto.InventoryDTO;
import com.li.tcc.demo.springcloud.inventory.service.InventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuan.li
 */
@RestController
@RequestMapping("/inventory")
public class InventoryController {

	private final InventoryService inventoryService;

	@Autowired
	public InventoryController(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}

	@RequestMapping("/decrease")
	public Boolean decrease(@RequestBody InventoryDTO inventoryDTO) {
		return inventoryService.decrease(inventoryDTO);
	}

	@RequestMapping("/findByProductId")
	public Integer findByProductId(@RequestParam("productId") String productId) {
		return inventoryService.findByProductId(productId).getTotalInventory();
	}

	@RequestMapping("/mockWithTryException")
	public Boolean mockWithTryException(@RequestBody InventoryDTO inventoryDTO) {
		return inventoryService.mockWithTryException(inventoryDTO);
	}

	@RequestMapping("/mockWithTryTimeout")
	public Boolean mockWithTryTimeout(@RequestBody InventoryDTO inventoryDTO) {
		return inventoryService.mockWithTryTimeout(inventoryDTO);
	}

}
