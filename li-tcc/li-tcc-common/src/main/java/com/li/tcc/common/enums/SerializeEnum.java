package com.li.tcc.common.enums;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * SerializeEnum
 * 
 * @author yuan.li
 */
public enum SerializeEnum {

	/**
	 * Jdk serialize protocol enum
	 */
	JDK("jdk"),

	/**
	 * Kryo serialize protocol enum
	 */
	KRYO("kryo"),

	/**
	 * Hessian serialize protocol enum
	 */
	HESSIAN("hessian"),

	/**
	 * Protostuff serialize protocol enum
	 */
	PROTOSTUFF("protostuff");

	private final String serialize;

	private SerializeEnum(final String serialize) {
		this.serialize = serialize;
	}

	public String getSerialize() {
		return serialize;
	}

	public static SerializeEnum getEnum(final String serialize) {
		Optional<SerializeEnum> serializeEnum = Arrays.stream(SerializeEnum.values())
				.filter(v -> Objects.equals(v.getSerialize(), serialize)).findFirst();
		return serializeEnum.orElse(SerializeEnum.KRYO);
	}

}
