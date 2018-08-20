package com.li.tcc.common.enums;

import java.util.Arrays;
import java.util.Objects;

/**
 * TccActionEnum
 *
 * @author yuan.li
 */
public enum TccActionEnum {

	/**
	 * Pre try tcc action enum
	 */
	PRE_TRY(0, "开始执行try"),

	/**
	 * Trying tcc action enum
	 */
	TRYING(1, "try阶段完成"),

	/**
	 * Confirming tcc action enum
	 */
	CONFIRMING(2, "confirm阶段"),

	/**
	 * Canceling tcc action enum
	 */
	CANCELING(3, "cancel阶段");

	private final Integer code;

	private final String desc;

	private TccActionEnum(final Integer code, final String desc) {
		this.code = code;
		this.desc = desc;
	}

	public Integer getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public static TccActionEnum getEnum(final int code) {
		return Arrays.stream(TccActionEnum.values()).filter(v -> Objects.equals(v.getCode(), code)).findFirst()
				.orElse(TccActionEnum.TRYING);
	}

}
