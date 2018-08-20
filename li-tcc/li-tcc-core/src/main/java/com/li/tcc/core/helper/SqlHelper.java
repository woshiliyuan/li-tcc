package com.li.tcc.core.helper;

import com.li.tcc.common.constant.CommonConstant;
import com.li.tcc.common.utils.DbTypeUtils;

/**
 * SqlHelper
 *
 * @author yuan.li
 */
public class SqlHelper {

	/**
	 * create table sql
	 *
	 * @param driverClassName
	 * @param tableName
	 * @return sql
	 */
	public static String buildCreateTableSql(final String driverClassName, final String tableName) {
		StringBuilder createTableSql = new StringBuilder();
		String dbType = DbTypeUtils.buildByDriverClassName(driverClassName);
		switch (dbType) {
		case CommonConstant.DB_MYSQL:
			createTableSql.append("CREATE TABLE IF NOT EXISTS `").append(tableName).append("` (")
					.append("  `trans_id` varchar(64) NOT NULL,").append("  `target_class` varchar(256) ,")
					.append("  `target_method` varchar(128) ,").append("  `confirm_method` varchar(128) ,")
					.append("  `cancel_method` varchar(128) ,").append("  `retried_count` tinyint NOT NULL,")
					.append("  `create_time` datetime NOT NULL,").append("  `last_time` datetime NOT NULL,")
					.append("  `version` tinyint NOT NULL,").append("  `status` tinyint NOT NULL,")
					.append("  `invocation` longblob,").append("  `role` tinyint NOT NULL,")
					.append("  `pattern` tinyint,").append("  PRIMARY KEY (`trans_id`))");
			break;
		case CommonConstant.DB_ORACLE:
			createTableSql.append("CREATE TABLE IF NOT EXISTS `").append(tableName).append("` (")
					.append("  `trans_id` varchar(64) NOT NULL,").append("  `target_class` varchar(256) ,")
					.append("  `target_method` varchar(128) ,").append("  `confirm_method` varchar(128) ,")
					.append("  `cancel_method` varchar(128) ,").append("  `retried_count` int(3) NOT NULL,")
					.append("  `create_time` date NOT NULL,").append("  `last_time` date NOT NULL,")
					.append("  `version` int(6) NOT NULL,").append("  `status` int(2) NOT NULL,")
					.append("  `invocation` BLOB ,").append("  `role` int(2) NOT NULL,").append("  `pattern` int(2),")
					.append("  PRIMARY KEY (`trans_id`))");
			break;
		default:
			throw new RuntimeException("dbType not support ! The current support mysql oracle");
		}
		return createTableSql.toString();
	}

}
