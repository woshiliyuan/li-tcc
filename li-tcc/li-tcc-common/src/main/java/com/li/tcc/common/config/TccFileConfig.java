package com.li.tcc.common.config;

/**
 * 文件保存事务日志配置
 * 
 * @author yuan.li
 */
public class TccFileConfig {

	/**
	 * 文件保存路径
	 */
	private String path;

	/**
	 * 文件前缀
	 */
	private String prefix;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

}
