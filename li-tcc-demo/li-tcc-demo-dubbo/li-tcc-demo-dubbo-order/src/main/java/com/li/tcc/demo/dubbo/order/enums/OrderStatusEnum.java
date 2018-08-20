package com.li.tcc.demo.dubbo.order.enums;

/**
 * OrderStatusEnum
 *
 * @author yuan.li
 */
public enum OrderStatusEnum {

	/**
	 * Not pay order status enum
	 */
	NOT_PAY(1, "未支付"),

	/**
	 * Paying order status enum
	 */
	PAYING(2, "支付中"),

	/**
	 * Pay fail order status enum
	 */
	PAY_FAIL(3, "支付失败"),

	/**
	 * Pay success order status enum
	 */
	PAY_SUCCESS(4, "支付成功");

	private int code;

	private String desc;

	OrderStatusEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
