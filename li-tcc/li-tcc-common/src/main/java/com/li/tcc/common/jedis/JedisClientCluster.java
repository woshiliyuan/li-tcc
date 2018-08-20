package com.li.tcc.common.jedis;

import redis.clients.jedis.JedisCluster;

import java.util.Set;

/**
 * JedisClientCluster
 * 
 * @author yuan.li
 */
public class JedisClientCluster implements JedisClient {

	private JedisCluster jedisCluster;

	public JedisClientCluster(final JedisCluster jedisCluster) {
		this.jedisCluster = jedisCluster;
	}

	@Override
	public String set(final String key, final String value) {
		return jedisCluster.set(key, value);
	}

	@Override
	public String set(final String key, final byte[] value) {
		return jedisCluster.set(key.getBytes(), value);
	}

	@Override
	public Long del(final String... keys) {
		return jedisCluster.del(keys);
	}

	@Override
	public String get(final String key) {
		return jedisCluster.get(key);
	}

	@Override
	public byte[] get(final byte[] key) {
		return jedisCluster.get(key);
	}

	@Override
	public Set<byte[]> keys(final byte[] pattern) {
		return jedisCluster.hkeys(pattern);
	}

	@Override
	public Set<String> keys(final String key) {
		return jedisCluster.hkeys(key);
	}

	@Override
	public Long hset(final String key, final String item, final String value) {
		return jedisCluster.hset(key, item, value);
	}

	@Override
	public String hget(final String key, final String item) {
		return jedisCluster.hget(key, item);
	}

	@Override
	public Long hdel(final String key, final String item) {
		return jedisCluster.hdel(key, item);
	}

	@Override
	public Long incr(final String key) {
		return jedisCluster.incr(key);
	}

	@Override
	public Long decr(final String key) {
		return jedisCluster.decr(key);
	}

	@Override
	public Long expire(final String key, final int second) {
		return jedisCluster.expire(key, second);
	}

	@Override
	public Set<String> zrange(final String key, final long start, final long end) {
		return jedisCluster.zrange(key, start, end);
	}

}
