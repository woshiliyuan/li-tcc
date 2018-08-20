package com.li.tcc.common.enums;

/**
 * EventTypeEnum
 *
 * @author yuan.li
 */
public enum EventTypeEnum {

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
	UPDATE_STATUS(2, "更新状态"),

	/**
	 * Rollback coordinator action enum
	 */
	UPDATE_PARTICIPANT(3, "更新参与者");

	private final Integer code;

	private final String desc;

	private EventTypeEnum(final Integer code, final String desc) {
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
