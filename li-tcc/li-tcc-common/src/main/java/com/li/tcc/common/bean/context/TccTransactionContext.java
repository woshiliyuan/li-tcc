package com.li.tcc.common.bean.context;

import java.io.Serializable;

/**
 * TccTransactionContext
 * 
 * @author yuan.li
 */
public class TccTransactionContext implements Serializable {

	private static final long serialVersionUID = -5289080166922118073L;

	/**
	 * transId
	 */
	private String transId;

	/**
	 * 事务执行动作 TccActionEnum
	 */
	private int action;

	/**
	 * 事务参与的角色 TccRoleEnum
	 */
	private int role;

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

}
