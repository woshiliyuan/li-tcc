package com.li.tcc.common.enums;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * BlockingQueueTypeEnum
 *
 * @author yuan.li
 */
public enum BlockingQueueTypeEnum {

	/**
	 * Linked blocking queue blocking queue type enum
	 */
	LINKED_BLOCKING_QUEUE("Linked"),
	/**
	 * Array blocking queue blocking queue type enum
	 */
	ARRAY_BLOCKING_QUEUE("Array"),
	/**
	 * Synchronous queue blocking queue type enum
	 */
	SYNCHRONOUS_QUEUE("SynchronousQueue");

	private String value;

	private BlockingQueueTypeEnum(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static BlockingQueueTypeEnum getEnum(final String value) {
		Optional<BlockingQueueTypeEnum> blockingQueueTypeEnum = Arrays.stream(BlockingQueueTypeEnum.values())
				.filter(v -> Objects.equals(v.getValue(), value)).findFirst();
		return blockingQueueTypeEnum.orElse(BlockingQueueTypeEnum.LINKED_BLOCKING_QUEUE);
	}

	@Override
	public String toString() {
		return value;
	}
}
