package com.li.tcc.demo.dubbo.order.service;

import com.li.tcc.demo.dubbo.order.entity.Order;

/**
 * @author yuan.li
 */
public interface PaymentService {

	/**
	 * 订单支付
	 *
	 * @param order
	 *            订单实体
	 */
	void makePayment(Order order);

	/**
	 * 订单支付
	 *
	 * @param order
	 *            订单实体
	 */
	void makePaymentWithNested(Order order);

	/**
	 * mock订单支付的时候库存异常
	 *
	 * @param order
	 *            订单实体
	 * @return String
	 */
	String mockPaymentInventoryWithTryException(Order order);

	/**
	 * mock订单支付的时候库存超时
	 *
	 * @param order
	 *            订单实体
	 * @return String
	 */
	String mockPaymentInventoryWithTryTimeout(Order order);

	/**
	 * mock订单支付的时候库存确认异常
	 *
	 * @param order
	 *            订单实体
	 * @return String
	 */
	String mockPaymentInventoryWithConfirmException(Order order);

	/**
	 * mock订单支付的时候库存确认超时
	 *
	 * @param order
	 *            订单实体
	 * @return String
	 */
	String mockPaymentInventoryWithConfirmTimeout(Order order);

}
