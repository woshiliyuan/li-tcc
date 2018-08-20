package com.li.tcc.core.concurrent.threadlocal;

import com.li.tcc.common.bean.context.TccTransactionContext;

/**
 * this is save transactionContext in threadLocal
 * 
 * @author yuan.li
 */
public final class TransactionContextLocal {

	private static final ThreadLocal<TccTransactionContext> CURRENT_LOCAL = new ThreadLocal<>();

	private static final TransactionContextLocal TRANSACTION_CONTEXT_LOCAL = new TransactionContextLocal();

	private TransactionContextLocal() {

	}

	/**
	 * singleton TransactionContextLocal
	 * 
	 * @return this
	 */
	public static TransactionContextLocal getInstance() {
		return TRANSACTION_CONTEXT_LOCAL;
	}

	/**
	 * set value
	 * 
	 * @param context
	 */
	public void set(final TccTransactionContext context) {
		CURRENT_LOCAL.set(context);
	}

	/**
	 * get value
	 * 
	 * @return TccTransactionContext
	 */
	public TccTransactionContext get() {
		return CURRENT_LOCAL.get();
	}

	/**
	 * clean threadLocal for gc
	 */
	public void remove() {
		CURRENT_LOCAL.remove();
	}
}
