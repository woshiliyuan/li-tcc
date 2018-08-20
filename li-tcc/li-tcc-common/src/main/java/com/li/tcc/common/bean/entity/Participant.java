package com.li.tcc.common.bean.entity;

import java.io.Serializable;

/**
 * Participant 参与者
 * 
 * @author yuan.li
 */
public class Participant implements Serializable {

	private static final long serialVersionUID = -2590970715288987627L;

	private String transId;

	private TccInvocation confirmTccInvocation;

	private TccInvocation cancelTccInvocation;

	public Participant() {

	}

	public Participant(String transId, TccInvocation confirmInvocation, TccInvocation cancelInvocation) {
		this.transId = transId;
		this.confirmTccInvocation = confirmInvocation;
		this.cancelTccInvocation = cancelInvocation;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public TccInvocation getConfirmTccInvocation() {
		return confirmTccInvocation;
	}

	public void setConfirmTccInvocation(TccInvocation confirmTccInvocation) {
		this.confirmTccInvocation = confirmTccInvocation;
	}

	public TccInvocation getCancelTccInvocation() {
		return cancelTccInvocation;
	}

	public void setCancelTccInvocation(TccInvocation cancelTccInvocation) {
		this.cancelTccInvocation = cancelTccInvocation;
	}

}
