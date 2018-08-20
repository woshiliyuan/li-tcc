package com.li.tcc.common.exception;

/**
 * TccException
 * 
 * @author yuan.li
 */
public class TccException extends Exception {

	private static final long serialVersionUID = -948934144333391208L;

	public TccException() {
	}

	public TccException(final String message) {
		super(message);
	}

	public TccException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public TccException(final Throwable cause) {
		super(cause);
	}
}
