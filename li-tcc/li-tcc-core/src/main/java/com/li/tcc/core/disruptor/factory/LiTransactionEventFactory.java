package com.li.tcc.core.disruptor.factory;

import com.li.tcc.core.disruptor.event.LiTransactionEvent;
import com.lmax.disruptor.EventFactory;

/**
 * LiTransactionEventFactory
 * 
 * @author yuan.li
 */
public class LiTransactionEventFactory implements EventFactory<LiTransactionEvent> {

	@Override
	public LiTransactionEvent newInstance() {
		return new LiTransactionEvent();
	}
}
