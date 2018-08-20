package com.li.tcc.springcloud.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.li.tcc.common.bean.context.TccTransactionContext;
import com.li.tcc.common.constant.CommonConstant;
import com.li.tcc.common.utils.GsonUtils;
import com.li.tcc.common.utils.LogUtil;
import com.li.tcc.core.interceptor.TccTransactionInterceptor;
import com.li.tcc.core.service.LiTransactionAspectService;

import javax.servlet.http.HttpServletRequest;

/**
 * SpringCloudLiTransactionInterceptor
 *
 * @author yuan.li
 */
@Component
public class SpringCloudLiTransactionInterceptor implements TccTransactionInterceptor {

	/**
	 * logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringCloudLiTransactionInterceptor.class);

	private final LiTransactionAspectService liTransactionAspectService;

	@Autowired
	public SpringCloudLiTransactionInterceptor(final LiTransactionAspectService liTransactionAspectService) {
		this.liTransactionAspectService = liTransactionAspectService;
	}

	@Override
	public Object interceptor(final ProceedingJoinPoint pjp) throws Throwable {
		TccTransactionContext tccTransactionContext;
		// 如果不是本地反射调用补偿
		RequestAttributes requestAttributes = null;
		try {
			requestAttributes = RequestContextHolder.currentRequestAttributes();
		} catch (Throwable ex) {
			LogUtil.warn(LOGGER, () -> "can not acquire request info:" + ex.getLocalizedMessage());
		}

		HttpServletRequest request = requestAttributes == null ? null : ((ServletRequestAttributes) requestAttributes)
				.getRequest();
		String context = request == null ? null : request.getHeader(CommonConstant.TCC_TRANSACTION_CONTEXT);
		tccTransactionContext = GsonUtils.getInstance().fromJson(context, TccTransactionContext.class);
		return liTransactionAspectService.invoke(tccTransactionContext, pjp);
	}

}
