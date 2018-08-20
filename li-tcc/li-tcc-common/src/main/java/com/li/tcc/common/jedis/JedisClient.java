package com.li.tcc.common.jedis;

import java.util.Set;

/**
 * JedisClient
 * 
 * @author yuan.li
 */
public interface JedisClient {

	/**
	 * set 操作
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	String set(String key, String value);

	/**
	 * set 操作
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	String set(String key, byte[] value);

	/**
	 * 批量删除key
	 * 
	 * @param keys
	 * @return
	 */
	Long del(String... keys);

	/**
	 * 根据key获取
	 * 
	 * @param key
	 * @return
	 */
	String get(String key);

	/**
	 * 根据key获取
	 * 
	 * @param key
	 * @return
	 */
	byte[] get(byte[] key);

	/**
	 * 根据key 模糊匹配
	 * 
	 * @param pattern
	 * @return
	 */
	Set<byte[]> keys(byte[] pattern);

	/**
	 * 根据key 模糊匹配
	 * 
	 * @param key
	 * @return
	 */
	Set<String> keys(String key);

	/**
	 * hash set值
	 * 
	 * @param key
	 * @param item
	 * @param value
	 * @return
	 */
	Long hset(String key, String item, String value);

	/**
	 * hash get 值
	 * 
	 * @param key
	 * @param item
	 * @return
	 */
	String hget(String key, String item);

	/**
	 * hash del 值
	 * 
	 * @param key
	 * @param item
	 * @return
	 */
	Long hdel(String key, String item);

	/**
	 * 增加
	 * 
	 * @param key
	 * @return
	 */
	Long incr(String key);

	/**
	 * 减少
	 * 
	 * @param key
	 * @return
	 */
	Long decr(String key);

	/**
	 * 设置key的过期时间
	 * 
	 * @param key
	 * @param second
	 * @return
	 */
	Long expire(String key, int second);

	/**
	 * 分页获取zsort
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	Set<String> zrange(String key, long start, long end);

}
