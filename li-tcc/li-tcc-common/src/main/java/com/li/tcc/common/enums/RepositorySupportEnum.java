package com.li.tcc.common.enums;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * RepositorySupportEnum
 * 
 * @author yuan.li
 */
public enum RepositorySupportEnum {

	/**
	 * Db compensate cache type enum
	 */
	DB("db"),

	/**
	 * File compensate cache type enum
	 */
	FILE("file"),

	/**
	 * Redis compensate cache type enum
	 */
	REDIS("redis"),

	/**
	 * Mongodb compensate cache type enum
	 */
	MONGODB("mongodb"),

	/**
	 * Zookeeper compensate cache type enum
	 */
	ZOOKEEPER("zookeeper");

	private final String support;

	private RepositorySupportEnum(final String support) {
		this.support = support;
	}

	public String getSupport() {
		return support;
	}

	public static RepositorySupportEnum getEnum(final String support) {
		Optional<RepositorySupportEnum> repositorySupportEnum = Arrays.stream(RepositorySupportEnum.values())
				.filter(v -> Objects.equals(v.getSupport(), support)).findFirst();
		return repositorySupportEnum.orElse(RepositorySupportEnum.DB);
	}
}
