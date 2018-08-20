package com.li.tcc.demo.dubbo.order.service;

import com.li.tcc.demo.dubbo.order.entity.Order;

import java.math.BigDecimal;

/**
 * @author yuan.li
 */
public interface OrderService {

	/**
	 * 创建订单并且进行扣除账户余额支付，并进行库存扣减操作
	 *
	 * @param count
	 *            购买数量
	 * @param amount
	 *            支付金额
	 * @return string
	 */
	String orderPay(Integer count, BigDecimal amount);

	/**
	 * 创建订单并且进行扣除账户余额支付，并进行库存扣减操作 in this Inventory nested in account.
	 * 
	 * @param count
	 *            购买数量
	 * @param amount
	 *            支付金额
	 * @return string
	 */
	String orderPayWithNested(Integer count, BigDecimal amount);

	/**
	 * 模拟在订单支付操作中，库存在try阶段中的库存异常
	 *
	 * @param count
	 *            购买数量
	 * @param amount
	 *            支付金额
	 * @return string
	 */
	String mockInventoryWithTryException(Integer count, BigDecimal amount);

	/**
	 * 模拟在订单支付操作中，库存在try阶段中的timeout
	 *
	 * @param count
	 *            购买数量
	 * @param amount
	 *            支付金额
	 * @return string
	 */
	String mockInventoryWithTryTimeout(Integer count, BigDecimal amount);

	/**
	 * 模拟在订单支付操作中，库存在Confirm阶段中的异常
	 *
	 * @param count
	 *            购买数量
	 * @param amount
	 *            支付金额
	 * @return string
	 */
	String mockInventoryWithConfirmException(Integer count, BigDecimal amount);

	/**
	 * 模拟在订单支付操作中，库存在Confirm阶段中的timeout
	 *
	 * @param count
	 *            购买数量
	 * @param amount
	 *            支付金额
	 * @return string
	 */
	String mockInventoryWithConfirmTimeout(Integer count, BigDecimal amount);

	/**
	 * 更新订单状态
	 *
	 * @param order
	 *            订单实体类
	 */
	void updateOrderStatus(Order order);
}
