package com.li.tcc.common.enums;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * RejectedPolicyTypeEnum
 *
 * @author yuan.li
 */
public enum RejectedPolicyTypeEnum {
	/**
	 * Abort policy rejected policy type enum
	 */
	ABORT_POLICY("Abort"),
	/**
	 * Blocking policy rejected policy type enum
	 */
	BLOCKING_POLICY("Blocking"),
	/**
	 * Caller runs policy rejected policy type enum
	 */
	CALLER_RUNS_POLICY("CallerRuns"),
	/**
	 * Discarded policy rejected policy type enum
	 */
	DISCARDED_POLICY("Discarded"),
	/**
	 * Rejected policy rejected policy type enum
	 */
	REJECTED_POLICY("Rejected");

	private final String value;

	private RejectedPolicyTypeEnum(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static RejectedPolicyTypeEnum getEnum(final String value) {
		Optional<RejectedPolicyTypeEnum> rejectedPolicyTypeEnum = Arrays.stream(RejectedPolicyTypeEnum.values())
				.filter(v -> Objects.equals(v.getValue(), value)).findFirst();
		return rejectedPolicyTypeEnum.orElse(RejectedPolicyTypeEnum.ABORT_POLICY);
	}
}
