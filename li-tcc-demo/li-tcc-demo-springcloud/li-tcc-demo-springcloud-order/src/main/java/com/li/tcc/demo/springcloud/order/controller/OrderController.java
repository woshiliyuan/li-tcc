package com.li.tcc.demo.springcloud.order.controller;

import com.li.tcc.demo.springcloud.order.service.OrderService;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author yuan.li
 */
@RestController
@RequestMapping("/order")
public class OrderController {

	private final OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping(value = "/orderPay")
	@ApiOperation(value = "订单支付接口（注意这里模拟的是创建订单并进行支付扣减库存等操作）")
	public String orderPay(@RequestParam(value = "count") Integer count,
			@RequestParam(value = "amount") BigDecimal amount) {

		return orderService.orderPay(count, amount);

	}

	@PostMapping(value = "/mockInventoryWithTryException")
	@ApiOperation(value = "模拟下单付款操作在try阶段异常，此时账户系统和订单状态会回滚，达到数据的一致性（注意:这里模拟的是系统异常，或者rpc异常）")
	public String mockInventoryWithTryException(@RequestParam(value = "count") Integer count,
			@RequestParam(value = "amount") BigDecimal amount) {
		return orderService.mockInventoryWithTryException(count, amount);
	}

	@PostMapping(value = "/mockInventoryWithTryTimeout")
	@ApiOperation(value = "模拟下单付款操作在try阶段超时异常，此时账户系统和订单状态会回滚，达到数据的一致性（异常指的是超时异常）")
	public String mockInventoryWithTryTimeout(@RequestParam(value = "count") Integer count,
			@RequestParam(value = "amount") BigDecimal amount) {
		return orderService.mockInventoryWithTryTimeout(count, amount);
	}

}
