package com.li.tcc.common.annotation;

/**
 * PropagationEnum
 * 
 * @author yuan.li
 */

public enum PropagationEnum {

	/**
	 * PropagationEnum required propagation
	 */
	PROPAGATION_REQUIRED(0),

	/**
	 * PropagationEnum supports propagation
	 */
	PROPAGATION_SUPPORTS(1),

	/**
	 * PropagationEnum mandatory propagation
	 */
	PROPAGATION_MANDATORY(2),

	/**
	 * PropagationEnum requires new propagation
	 */
	PROPAGATION_REQUIRES_NEW(3),

	/**
	 * PropagationEnum not supported propagation
	 */
	PROPAGATION_NOT_SUPPORTED(4),

	/**
	 * PropagationEnum never propagation
	 */
	PROPAGATION_NEVER(5),

	/**
	 * PropagationEnum nested propagation
	 */
	PROPAGATION_NESTED(6);

	private final Integer value;

	private PropagationEnum(Integer value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

}
