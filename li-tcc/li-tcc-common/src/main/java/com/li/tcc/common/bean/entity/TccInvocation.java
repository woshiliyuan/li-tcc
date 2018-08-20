package com.li.tcc.common.bean.entity;

import java.io.Serializable;

/**
 * TccInvocation 封装方法调用点
 * 
 * @author yuan.li
 */
@SuppressWarnings("rawtypes")
public class TccInvocation implements Serializable {

	private static final long serialVersionUID = -5108578223428529356L;

	private Class targetClass;

	private String methodName;

	private Class[] parameterTypes;

	private Object[] args;

	public TccInvocation() {
	}

	public TccInvocation(Class<?> clazz, String confirmMethodName, Class<?>[] parameterTypes, Object[] args) {
		this.targetClass = clazz;
		this.methodName = confirmMethodName;
		this.parameterTypes = parameterTypes;
		this.args = args;
	}

	public Class getTargetClass() {
		return targetClass;
	}

	public String getMethodName() {
		return methodName;
	}

	public Class[] getParameterTypes() {
		return parameterTypes;
	}

	public Object[] getArgs() {
		return args;
	}

}
