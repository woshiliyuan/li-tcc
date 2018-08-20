package com.li.tcc.core.disruptor.handler;

import com.li.tcc.common.bean.entity.TccTransaction;
import com.li.tcc.common.enums.EventTypeEnum;
import com.li.tcc.core.coordinator.CoordinatorService;
import com.li.tcc.core.disruptor.event.LiTransactionEvent;
import com.lmax.disruptor.EventHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Disroptor handler
 * 
 * @author yuan.li
 */
@Component
public class LiTransactionEventHandler implements EventHandler<LiTransactionEvent> {

	@Autowired
	private CoordinatorService coordinatorService;

	@Override
	public void onEvent(final LiTransactionEvent liTransactionEvent, final long sequence, final boolean endOfBatch) {
		if (liTransactionEvent.getType() == EventTypeEnum.SAVE.getCode()) {
			coordinatorService.save(liTransactionEvent.getTccTransaction());
		} else if (liTransactionEvent.getType() == EventTypeEnum.UPDATE_PARTICIPANT.getCode()) {
			coordinatorService.updateParticipant(liTransactionEvent.getTccTransaction());
		} else if (liTransactionEvent.getType() == EventTypeEnum.UPDATE_STATUS.getCode()) {
			final TccTransaction tccTransaction = liTransactionEvent.getTccTransaction();
			coordinatorService.updateStatus(tccTransaction.getTransId(), tccTransaction.getStatus());
		} else if (liTransactionEvent.getType() == EventTypeEnum.DELETE.getCode()) {
			coordinatorService.remove(liTransactionEvent.getTccTransaction().getTransId());
		}
		liTransactionEvent.clear();
	}
}
