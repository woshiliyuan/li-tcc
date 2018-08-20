package com.li.tcc.common.utils;

import com.li.tcc.common.constant.CommonConstant;

/**
 * DbTypeUtils
 * 
 * @author yuan.li
 */
public class DbTypeUtils {
	/**
	 * check db type
	 * 
	 * @param driverClassName
	 * @return mysql oracle
	 */
	public static String buildByDriverClassName(final String driverClassName) {
		String dbType = null;
		if (driverClassName.contains(CommonConstant.DB_MYSQL)) {
			dbType = CommonConstant.DB_MYSQL;
		} else if (driverClassName.contains(CommonConstant.DB_ORACLE)) {
			dbType = CommonConstant.DB_ORACLE;
		}
		return dbType;
	}

}
