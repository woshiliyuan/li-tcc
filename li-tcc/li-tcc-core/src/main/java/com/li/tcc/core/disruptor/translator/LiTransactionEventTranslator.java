package com.li.tcc.core.disruptor.translator;

import com.li.tcc.common.bean.entity.TccTransaction;
import com.li.tcc.core.disruptor.event.LiTransactionEvent;
import com.lmax.disruptor.EventTranslatorOneArg;

/**
 * EventTranslator
 * 
 * @author yuan.li
 */
public class LiTransactionEventTranslator implements EventTranslatorOneArg<LiTransactionEvent, TccTransaction> {

	private int type;

	public LiTransactionEventTranslator(final int type) {
		this.type = type;
	}

	@Override
	public void translateTo(final LiTransactionEvent liTransactionEvent, final long l,
			final TccTransaction tccTransaction) {
		liTransactionEvent.setTccTransaction(tccTransaction);
		liTransactionEvent.setType(type);
	}
}
