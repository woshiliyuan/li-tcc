package com.li.tcc.common.utils.httpclient;

import java.io.Serializable;

/**
 * AjaxResponse
 * 
 * @author yuan.li
 **/
public class AjaxResponse implements Serializable {

	private static final long serialVersionUID = -2792556188993845048L;

	private int code;

	private String message;

	private Object data;

	public AjaxResponse(int code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public static AjaxResponse success() {
		return success("");
	}

	public static AjaxResponse success(String msg) {
		return success(msg, null);
	}

	public static AjaxResponse success(Object data) {
		return success(null, data);
	}

	public static AjaxResponse success(String msg, Object data) {
		return get(CommonErrorCode.SUCCESSFUL, msg, data);
	}

	public static AjaxResponse error(String msg) {
		return error(CommonErrorCode.ERROR, msg);
	}

	public static AjaxResponse error(int code, String msg) {
		return get(code, msg, null);
	}

	public static AjaxResponse get(int code, String msg, Object data) {
		return new AjaxResponse(code, msg, data);
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "AjaxResponse [code=" + code + ", message=" + message + ", data=" + data + "]";
	}

}
