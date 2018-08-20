package com.li.tcc.admin.dto;

import java.io.Serializable;
import java.util.List;

/**
 * CompensationDTO
 * 
 * @author yuan.li
 */
public class CompensationDTO implements Serializable {

	private static final long serialVersionUID = -285004540347737641L;

	private String applicationName;

	private List<String> ids;

	private String id;

	private Integer retry;

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getRetry() {
		return retry;
	}

	public void setRetry(Integer retry) {
		this.retry = retry;
	}
}
