package com.li.tcc.admin.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.li.tcc.admin.annotation.Permission;
import com.li.tcc.admin.service.login.LoginServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * AuthInterceptor
 * 
 * @author yuan.li
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			final Permission annotation = method.getAnnotation(Permission.class);
			if (Objects.isNull(annotation)) {
				return Boolean.TRUE;
			}
			final boolean login = annotation.isLogin();
			if (login) {
				if (!LoginServiceImpl.LOGIN_SUCCESS) {
					request.setAttribute("code", "404");
					request.setAttribute("msg", "请您先登录！");
					request.getRequestDispatcher("/").forward(request, response);
					return Boolean.FALSE;
				}
			}
		}
		return super.preHandle(request, response, handler);
	}

}
