package com.li.tcc.admin.vo;

import java.io.Serializable;

/**
 * TccCompensationVO
 * 
 * @author yuan.li
 */
public class TccCompensationVO implements Serializable {

	private static final long serialVersionUID = 564418979137349581L;

	private String transId;

	private Integer retriedCount;

	private String createTime;

	private String lastTime;

	private Integer version;

	private String targetClass;

	private String targetMethod;

	private String confirmMethod;

	private String cancelMethod;

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public Integer getRetriedCount() {
		return retriedCount;
	}

	public void setRetriedCount(Integer retriedCount) {
		this.retriedCount = retriedCount;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastTime() {
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getTargetClass() {
		return targetClass;
	}

	public void setTargetClass(String targetClass) {
		this.targetClass = targetClass;
	}

	public String getTargetMethod() {
		return targetMethod;
	}

	public void setTargetMethod(String targetMethod) {
		this.targetMethod = targetMethod;
	}

	public String getConfirmMethod() {
		return confirmMethod;
	}

	public void setConfirmMethod(String confirmMethod) {
		this.confirmMethod = confirmMethod;
	}

	public String getCancelMethod() {
		return cancelMethod;
	}

	public void setCancelMethod(String cancelMethod) {
		this.cancelMethod = cancelMethod;
	}
}
