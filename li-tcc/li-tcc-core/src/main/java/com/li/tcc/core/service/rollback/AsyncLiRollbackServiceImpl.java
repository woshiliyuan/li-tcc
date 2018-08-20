package com.li.tcc.core.service.rollback;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.li.tcc.common.bean.context.TccTransactionContext;
import com.li.tcc.common.bean.entity.Participant;
import com.li.tcc.common.bean.entity.TccInvocation;
import com.li.tcc.common.enums.TccActionEnum;
import com.li.tcc.common.utils.LogUtil;
import com.li.tcc.core.concurrent.threadlocal.TransactionContextLocal;
import com.li.tcc.core.helper.SpringBeanUtils;
import com.li.tcc.core.service.LiRollbackService;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * AsyncLiRollbackServiceImpl
 * 
 * @author yuan.li
 */
@Component
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AsyncLiRollbackServiceImpl implements LiRollbackService {

	/**
	 * logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AsyncLiRollbackServiceImpl.class);

	/**
	 * 执行协调回滚方法
	 *
	 * @param participantList
	 *            需要协调的资源集合
	 */
	@Override
	public void execute(final List<Participant> participantList) {
		try {
			if (CollectionUtils.isNotEmpty(participantList)) {
				final CompletableFuture[] cfs = participantList.stream()
						.map(participant -> CompletableFuture.runAsync(() -> {
							TccTransactionContext context = new TccTransactionContext();
							context.setAction(TccActionEnum.CANCELING.getCode());
							context.setTransId(participant.getTransId());
							TransactionContextLocal.getInstance().set(context);
							try {
								executeParticipantMethod(participant.getCancelTccInvocation());
							} catch (Exception e) {
								LogUtil.error(LOGGER, "执行cancel方法异常：{}", e::getMessage);
								e.printStackTrace();
							}
						}).whenComplete((v, e) -> TransactionContextLocal.getInstance().remove()))
						.toArray(CompletableFuture[]::new);
				CompletableFuture.allOf(cfs).join();
			}
			LogUtil.debug(LOGGER, () -> "执行cancel方法成功！");
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error(LOGGER, "执行cancel方法异常：{}", e::getMessage);
		}

	}

	private void executeParticipantMethod(final TccInvocation tccInvocation) throws Exception {
		if (Objects.nonNull(tccInvocation)) {
			final Class clazz = tccInvocation.getTargetClass();
			final String method = tccInvocation.getMethodName();
			final Object[] args = tccInvocation.getArgs();
			final Class[] parameterTypes = tccInvocation.getParameterTypes();
			final Object bean = SpringBeanUtils.getInstance().getBean(clazz);
			LogUtil.debug(LOGGER, "开始执行：{}", () -> clazz.getName() + " ;" + method);
			MethodUtils.invokeMethod(bean, method, args, parameterTypes);
		}
	}
}
