package com.li.tcc.common.constant;

/**
 * CommonConstant
 *
 * @author yuan.li
 */
public final class CommonConstant {

	private CommonConstant() {
	}

	public final static String DB_MYSQL = "mysql";

	public final static String DB_ORACLE = "oracle";

	public final static String PATH_SUFFIX = "/tcc";

	public final static String DB_SUFFIX = "tcc_";

	public final static String RECOVER_REDIS_KEY_PRE = "tcc:transaction:%s";

	public final static String TCC_TRANSACTION_CONTEXT = "TCC_TRANSACTION_CONTEXT";

}
