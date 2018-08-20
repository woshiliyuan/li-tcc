package com.li.tcc.common.annotation;

/**
 * TccPatternEnum 当模式为tcc时候，在try异常中，会执行cancel方法，cc模式不会执行
 *
 * @author yuan.li
 */
public enum TccPatternEnum {

	/**
	 * Tcc tcc pattern enum
	 */
	TCC(1, "try,confirm,cancel模式"),

	/**
	 * Cc tcc pattern enum
	 */
	CC(2, "confirm,cancel模式");

	private Integer code;

	private String desc;

	TccPatternEnum(final Integer code, final String desc) {
		this.code = code;
		this.desc = desc;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
