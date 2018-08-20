package com.li.tcc.common.utils;

import com.li.tcc.common.constant.CommonConstant;

/**
 * 获取资源路径的工具类
 * 
 * @author yuan.li
 */
public class RepositoryPathUtils {

	public static String buildRedisKey(final String keyPrefix, final String id) {
		return String.join(":", keyPrefix, id);
	}

	public static String buildFilePath(final String applicationName) {
		return String.join("/", CommonConstant.PATH_SUFFIX, applicationName.replaceAll("-", "_"));
	}

	public static String getFullFileName(final String filePath, final String id) {
		return String.format("%s/%s", filePath, id);
	}

	public static String buildDbTableName(final String applicationName) {
		return CommonConstant.DB_SUFFIX + applicationName.replaceAll("-", "_");
	}

	public static String buildMongoTableName(final String applicationName) {
		return CommonConstant.DB_SUFFIX + applicationName.replaceAll("-", "_");
	}

	public static String buildRedisKeyPrefix(final String applicationName) {
		return String.format(CommonConstant.RECOVER_REDIS_KEY_PRE, applicationName);
	}

	public static String buildZookeeperPathPrefix(final String applicationName) {
		return String.join("-", CommonConstant.PATH_SUFFIX, applicationName);
	}

	public static String buildZookeeperRootPath(final String prefix, final String id) {
		return String.join("/", prefix, id);
	}

}
