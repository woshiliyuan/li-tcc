package com.li.tcc.admin.query;

import java.io.Serializable;

import com.li.tcc.admin.page.PageParameter;

/**
 * query condition
 * 
 * @author yuan.li
 */
public class CompensationQuery implements Serializable {

	private static final long serialVersionUID = 3297929795348894462L;

	/**
	 * app name
	 */
	private String applicationName;

	/**
	 * transId
	 */
	private String transId;

	/**
	 * retry
	 */
	private Integer retry;

	private PageParameter pageParameter;

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public Integer getRetry() {
		return retry;
	}

	public void setRetry(Integer retry) {
		this.retry = retry;
	}

	public PageParameter getPageParameter() {
		return pageParameter;
	}

	public void setPageParameter(PageParameter pageParameter) {
		this.pageParameter = pageParameter;
	}
}
