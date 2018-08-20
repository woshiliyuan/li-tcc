package com.li.tcc.common.utils.httpclient;

import org.apache.commons.lang3.StringUtils;

/**
 * 错误码定义类
 * 
 * @author yuan.li
 **/
public class CommonErrorCode {

	/**
	 * 操作失败全局定义定义
	 */
	public static final int ERROR = -2;

	/**
	 * 成功
	 */
	public static final int SUCCESSFUL = 200;

	/**
	 * 获取错误码描述信息
	 *
	 * @param code
	 *            错误码枚举的name,指向自定义的信息
	 * @return 描述信息
	 */
	public static String getErrorMsg(final int code) {
		// 获取错误信息
		String msg = System.getProperty(String.valueOf(code));
		if (StringUtils.isBlank(msg)) {
			return "根据传入的错误码[" + code + "]没有找到相应的错误信息.";
		}
		return msg;
	}
}
