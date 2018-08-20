package com.li.tcc.core.disruptor.event;

import java.io.Serializable;

import com.li.tcc.common.bean.entity.TccTransaction;

/**
 * LiTransactionEvent
 * 
 * @author yuan.li
 */
public class LiTransactionEvent implements Serializable {

	private static final long serialVersionUID = 1330242638992181789L;

	private TccTransaction tccTransaction;

	private int type;

	/**
	 * help gc
	 */
	public void clear() {
		tccTransaction = null;
	}

	public TccTransaction getTccTransaction() {
		return tccTransaction;
	}

	public void setTccTransaction(TccTransaction tccTransaction) {
		this.tccTransaction = tccTransaction;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
