package com.li.tcc.common.serializer;

import com.li.tcc.common.exception.TccException;

/**
 * ObjectSerializer
 * 
 * @author yuan.li
 */
public interface ObjectSerializer {

	/**
	 * 序列化对象
	 * 
	 * @param obj
	 * @return
	 * @throws TccException
	 */
	byte[] serialize(Object obj) throws TccException;

	/**
	 * 反序列化对象
	 * 
	 * @param param
	 * @param clazz
	 * @return
	 * @throws TccException
	 */
	<T> T deSerialize(byte[] param, Class<T> clazz) throws TccException;

	/**
	 * 设置scheme
	 *
	 * @return scheme 命名
	 */
	String getScheme();
}
