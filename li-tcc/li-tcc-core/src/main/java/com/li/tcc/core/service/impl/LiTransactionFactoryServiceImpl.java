package com.li.tcc.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.li.tcc.common.bean.context.TccTransactionContext;
import com.li.tcc.core.service.LiTransactionFactoryService;
import com.li.tcc.core.service.executor.LiTransactionExecutor;
import com.li.tcc.core.service.handler.ConsumeLiTransactionHandler;
import com.li.tcc.core.service.handler.ParticipantLiTransactionHandler;
import com.li.tcc.core.service.handler.StarterLiTransactionHandler;

import java.util.Objects;

/**
 * LiTransactionFactoryServiceImpl
 * 
 * @author yuan.li
 */
@Service("tccTransactionFactoryService")
@SuppressWarnings("rawtypes")
public class LiTransactionFactoryServiceImpl implements LiTransactionFactoryService {

	private final LiTransactionExecutor liTransactionExecutor;

	@Autowired
	public LiTransactionFactoryServiceImpl(final LiTransactionExecutor liTransactionExecutor) {
		this.liTransactionExecutor = liTransactionExecutor;
	}

	/**
	 * get LiTransactionHandler
	 *
	 * @param context
	 *            tcc事务上下文
	 * @return Class
	 */
	@Override
	public Class factoryOf(final TccTransactionContext context) {
		// 如果事务还没开启或者 tcc事务上下文是空， 那么应该进入发起调用
		if (!liTransactionExecutor.isBegin() && Objects.isNull(context)) {
			return StarterLiTransactionHandler.class;
		} else if (liTransactionExecutor.isBegin() && Objects.isNull(context)) {
			return ConsumeLiTransactionHandler.class;
		} else if (Objects.nonNull(context)) {
			return ParticipantLiTransactionHandler.class;
		}
		return ConsumeLiTransactionHandler.class;
	}
}
