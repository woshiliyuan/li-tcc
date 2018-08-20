package com.li.tcc.common.enums;

/**
 * CoordinatorActionEnum
 *
 * @author yuan.li
 */
public enum CoordinatorActionEnum {

	/**
	 * Save coordinator action enum
	 */
	SAVE(0, "保存"),

	/**
	 * Delete coordinator action enum
	 */
	DELETE(1, "删除"),

	/**
	 * Update coordinator action enum
	 */
	UPDATE(2, "更新"),

	/**
	 * Rollback coordinator action enum
	 */
	ROLLBACK(3, "回滚"),

	/**
	 * Compensation coordinator action enum
	 */
	COMPENSATION(4, "补偿");

	private final Integer code;

	private final String desc;

	private CoordinatorActionEnum(final Integer code, final String desc) {
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
