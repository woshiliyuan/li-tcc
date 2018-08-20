package com.li.tcc.core.service.impl;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.li.tcc.common.bean.context.TccTransactionContext;
import com.li.tcc.core.helper.SpringBeanUtils;
import com.li.tcc.core.service.LiTransactionAspectService;
import com.li.tcc.core.service.LiTransactionFactoryService;
import com.li.tcc.core.service.LiTransactionHandler;

/**
 * LiTransactionAspectServiceImpl
 * 
 * @author yuan.li
 */
@Service("tccTransactionAspectService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class LiTransactionAspectServiceImpl implements LiTransactionAspectService {

	private final LiTransactionFactoryService liTransactionFactoryService;

	@Autowired
	public LiTransactionAspectServiceImpl(final LiTransactionFactoryService liTransactionFactoryService) {
		this.liTransactionFactoryService = liTransactionFactoryService;
	}

	/**
	 * tcc 事务切面服务
	 *
	 * @param tccTransactionContext
	 *            tcc事务上下文对象
	 * @param point
	 *            切点
	 * @return object
	 * @throws Throwable
	 */
	@Override
	public Object invoke(final TccTransactionContext tccTransactionContext, final ProceedingJoinPoint point)
			throws Throwable {
		final Class clazz = liTransactionFactoryService.factoryOf(tccTransactionContext);
		final LiTransactionHandler txTransactionHandler = (LiTransactionHandler) SpringBeanUtils.getInstance().getBean(
				clazz);
		return txTransactionHandler.handler(point, tccTransactionContext);
	}
}
