package com.li.tcc.common.enums;

/**
 * TccRoleEnum
 *
 * @author yuan.li
 */
public enum TccRoleEnum {

	/**
	 * Start tcc role enum
	 */
	START(1, "发起者"),

	/**
	 * Consumer tcc role enum
	 */
	CONSUMER(2, "消费者"),

	/**
	 * Provider tcc role enum
	 */
	PROVIDER(3, "提供者"),

	/**
	 * Local tcc role enum
	 */
	LOCAL(4, "本地调用");

	private final Integer code;

	private final String desc;

	private TccRoleEnum(final Integer code, final String desc) {
		this.code = code;
		this.desc = desc;
	}

	public Integer getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

}
