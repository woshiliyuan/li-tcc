package com.li.tcc.demo.springcloud.order.service.impl;

import com.li.tcc.common.annotation.Tcc;
import com.li.tcc.common.exception.TccRuntimeException;
import com.li.tcc.demo.springcloud.order.client.AccountClient;
import com.li.tcc.demo.springcloud.order.client.InventoryClient;
import com.li.tcc.demo.springcloud.order.dto.AccountDTO;
import com.li.tcc.demo.springcloud.order.dto.InventoryDTO;
import com.li.tcc.demo.springcloud.order.entity.Order;
import com.li.tcc.demo.springcloud.order.enums.OrderStatusEnum;
import com.li.tcc.demo.springcloud.order.mapper.OrderMapper;
import com.li.tcc.demo.springcloud.order.service.PaymentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author yuan.li
 */
@Service
public class PaymentServiceImpl implements PaymentService {

	/**
	 * logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceImpl.class);

	private final OrderMapper orderMapper;

	private final AccountClient accountClient;

	private final InventoryClient inventoryClient;

	@Autowired(required = false)
	public PaymentServiceImpl(OrderMapper orderMapper, AccountClient accountClient, InventoryClient inventoryClient) {
		this.orderMapper = orderMapper;
		this.accountClient = accountClient;
		this.inventoryClient = inventoryClient;
	}

	@Override
	@Tcc(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
	public void makePayment(Order order) {

		order.setStatus(OrderStatusEnum.PAYING.getCode());
		orderMapper.update(order);
		// 检查数据
		final BigDecimal accountInfo = accountClient.findByUserId(order.getUserId());

		final Integer inventoryInfo = inventoryClient.findByProductId(order.getProductId());

		if (accountInfo.compareTo(order.getTotalAmount()) < 0) {
			throw new TccRuntimeException("余额不足！");
		}

		if (inventoryInfo < order.getCount()) {
			throw new TccRuntimeException("库存不足！");
		}

		// 扣除用户余额
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setAmount(order.getTotalAmount());
		accountDTO.setUserId(order.getUserId());

		LOGGER.debug("===========执行springcloud扣减资金接口==========");
		accountClient.payment(accountDTO);

		// 进入扣减库存操作
		InventoryDTO inventoryDTO = new InventoryDTO();
		inventoryDTO.setCount(order.getCount());
		inventoryDTO.setProductId(order.getProductId());
		inventoryClient.decrease(inventoryDTO);
	}

	@Override
	@Tcc(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
	public String mockPaymentInventoryWithTryException(Order order) {

		LOGGER.debug("===========执行springcloud  mockPaymentInventoryWithTryException 扣减资金接口==========");
		order.setStatus(OrderStatusEnum.PAYING.getCode());
		orderMapper.update(order);

		// 扣除用户余额
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setAmount(order.getTotalAmount());
		accountDTO.setUserId(order.getUserId());
		accountClient.payment(accountDTO);

		InventoryDTO inventoryDTO = new InventoryDTO();
		inventoryDTO.setCount(order.getCount());
		inventoryDTO.setProductId(order.getProductId());
		inventoryClient.mockWithTryException(inventoryDTO);
		return "success";
	}

	@Override
	@Tcc(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
	public String mockPaymentInventoryWithTryTimeout(Order order) {
		LOGGER.debug("===========执行springcloud  mockPaymentInventoryWithTryTimeout 扣减资金接口==========");
		order.setStatus(OrderStatusEnum.PAYING.getCode());
		orderMapper.update(order);

		// 扣除用户余额
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setAmount(order.getTotalAmount());
		accountDTO.setUserId(order.getUserId());
		accountClient.payment(accountDTO);

		InventoryDTO inventoryDTO = new InventoryDTO();
		inventoryDTO.setCount(order.getCount());
		inventoryDTO.setProductId(order.getProductId());
		inventoryClient.mockWithTryTimeout(inventoryDTO);
		return "success";
	}

	public void confirmOrderStatus(Order order) {

		order.setStatus(OrderStatusEnum.PAY_SUCCESS.getCode());
		orderMapper.update(order);
		LOGGER.info("=========进行订单confirm操作完成================");

	}

	public void cancelOrderStatus(Order order) {

		order.setStatus(OrderStatusEnum.PAY_FAIL.getCode());
		orderMapper.update(order);
		LOGGER.info("=========进行订单cancel操作完成================");
	}
}
