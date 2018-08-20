package com.li.tcc.core.disruptor.publisher;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.li.tcc.common.bean.entity.TccTransaction;
import com.li.tcc.core.concurrent.threadpool.LiThreadFactory;
import com.li.tcc.core.disruptor.event.LiTransactionEvent;
import com.li.tcc.core.disruptor.factory.LiTransactionEventFactory;
import com.li.tcc.core.disruptor.handler.LiTransactionEventHandler;
import com.li.tcc.core.disruptor.translator.LiTransactionEventTranslator;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

/**
 * event publisher
 *
 * @author yuan.li
 */
@Component
public class LiTransactionEventPublisher implements DisposableBean {

	private static final int MAX_THREAD = Runtime.getRuntime().availableProcessors() << 1;

	private Executor executor;

	private Disruptor<LiTransactionEvent> disruptor;

	private final LiTransactionEventHandler liTransactionEventHandler;

	@Autowired
	public LiTransactionEventPublisher(LiTransactionEventHandler liTransactionEventHandler) {
		this.liTransactionEventHandler = liTransactionEventHandler;
	}

	/**
	 * disruptor start
	 *
	 * @param bufferSize
	 *            this is disruptor buffer size
	 */
	public void start(final int bufferSize) {
		disruptor = new Disruptor<>(new LiTransactionEventFactory(), bufferSize, r -> {
			AtomicInteger index = new AtomicInteger(1);
			return new Thread(null, r, "disruptor-thread-" + index.getAndIncrement());
		}, ProducerType.MULTI, new BlockingWaitStrategy());

		executor = new ThreadPoolExecutor(MAX_THREAD, MAX_THREAD, 0, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<>(), LiThreadFactory.create("li-log-disruptor", false),
				new ThreadPoolExecutor.AbortPolicy());

		disruptor.handleEventsWith(liTransactionEventHandler);
		disruptor.start();
	}

	/**
	 * publish disruptor event
	 *
	 * @param tccTransaction
	 *            TccTransaction
	 * @param type
	 *            EventTypeEnum
	 */
	public void publishEvent(final TccTransaction tccTransaction, final int type) {
		executor.execute(() -> {
			final RingBuffer<LiTransactionEvent> ringBuffer = disruptor.getRingBuffer();
			ringBuffer.publishEvent(new LiTransactionEventTranslator(type), tccTransaction);
		});
	}

	@Override
	public void destroy() {
		disruptor.shutdown();
	}

}
