package com.li.tcc.common.utils;

import com.li.tcc.common.exception.TccRuntimeException;

/**
 * AssertUtils
 * 
 * @author yuan.li
 */
public final class AssertUtils {

	private AssertUtils() {

	}

	public static void notNull(final Object obj) {
		if (obj == null) {
			throw new TccRuntimeException("argument invalid,Please check");
		}
	}

}
