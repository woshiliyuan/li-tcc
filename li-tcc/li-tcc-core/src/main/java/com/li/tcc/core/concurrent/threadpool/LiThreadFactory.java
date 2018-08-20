package com.li.tcc.core.concurrent.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * custom thread factory
 * 
 * @author yuan.li
 */
public final class LiThreadFactory implements ThreadFactory {

	private static final AtomicLong THREAD_NUMBER = new AtomicLong(1);

	private static final ThreadGroup THREAD_GROUP = new ThreadGroup("liTransaction");

	private static volatile boolean daemon;

	private final String namePrefix;

	private LiThreadFactory(final String namePrefix, final boolean daemon) {
		this.namePrefix = namePrefix;
		LiThreadFactory.daemon = daemon;
	}

	/**
	 * create custom thread factory
	 * 
	 * @param namePrefix
	 * @param daemon
	 * @return
	 */
	public static ThreadFactory create(final String namePrefix, final boolean daemon) {
		return new LiThreadFactory(namePrefix, daemon);
	}

	@Override
	public Thread newThread(final Runnable runnable) {
		Thread thread = new Thread(THREAD_GROUP, runnable, THREAD_GROUP.getName() + "-" + namePrefix + "-"
				+ THREAD_NUMBER.getAndIncrement());
		thread.setDaemon(daemon);
		if (thread.getPriority() != Thread.NORM_PRIORITY) {
			thread.setPriority(Thread.NORM_PRIORITY);
		}
		return thread;
	}
}
