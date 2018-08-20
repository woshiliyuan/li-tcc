package com.li.tcc.common.config;

/**
 * zookeeper 保存事务日志配置
 * 
 * @author yuan.li
 */
public class TccZookeeperConfig {

	private String host;

	private int sessionTimeOut = 1000;

	private String rootPath = "/tcc";

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getSessionTimeOut() {
		return sessionTimeOut;
	}

	public void setSessionTimeOut(int sessionTimeOut) {
		this.sessionTimeOut = sessionTimeOut;
	}

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}
}
