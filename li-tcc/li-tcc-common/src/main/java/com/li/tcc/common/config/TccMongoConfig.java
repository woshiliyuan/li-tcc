package com.li.tcc.common.config;

/**
 * mongo 保存事务日志配置
 * 
 * @author yuan.li
 */
public class TccMongoConfig {

	/**
	 * mongo数据库设置
	 */
	private String mongoDbName;

	/**
	 * mongo数据库URL
	 */
	private String mongoDbUrl;

	/**
	 * mongo数据库用户名
	 */
	private String mongoUserName;

	/**
	 * mongo数据库密码
	 */
	private String mongoUserPwd;

	public String getMongoDbName() {
		return mongoDbName;
	}

	public void setMongoDbName(String mongoDbName) {
		this.mongoDbName = mongoDbName;
	}

	public String getMongoDbUrl() {
		return mongoDbUrl;
	}

	public void setMongoDbUrl(String mongoDbUrl) {
		this.mongoDbUrl = mongoDbUrl;
	}

	public String getMongoUserName() {
		return mongoUserName;
	}

	public void setMongoUserName(String mongoUserName) {
		this.mongoUserName = mongoUserName;
	}

	public String getMongoUserPwd() {
		return mongoUserPwd;
	}

	public void setMongoUserPwd(String mongoUserPwd) {
		this.mongoUserPwd = mongoUserPwd;
	}
}
