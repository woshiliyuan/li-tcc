package com.li.tcc.common.exception;

/**
 * TccRuntimeException
 * 
 * @author yuan.li
 */
public class TccRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -1949770547060521702L;

	public TccRuntimeException() {
	}

	public TccRuntimeException(final String message) {
		super(message);
	}

	public TccRuntimeException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public TccRuntimeException(final Throwable cause) {
		super(cause);
	}
}
