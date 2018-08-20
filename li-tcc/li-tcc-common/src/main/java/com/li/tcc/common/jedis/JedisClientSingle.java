package com.li.tcc.common.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * JedisClientSingle
 * 
 * @author yuan.li
 */
public class JedisClientSingle implements JedisClient {

	private JedisPool jedisPool;

	public JedisClientSingle(final JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	@Override
	public String set(final String key, final String value) {
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.set(key, value);
		}
	}

	@Override
	public String set(final String key, final byte[] value) {
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.set(key.getBytes(), value);
		}
	}

	@Override
	public Long del(final String... keys) {
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.del(keys);
		}
	}

	@Override
	public String get(final String key) {
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.get(key);
		}
	}

	@Override
	public byte[] get(final byte[] key) {
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.get(key);
		}
	}

	@Override
	public Set<byte[]> keys(final byte[] pattern) {
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.keys(pattern);
		}
	}

	@Override
	public Set<String> keys(final String key) {
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.keys(key);
		}
	}

	@Override
	public Long hset(final String key, final String item, final String value) {
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.hset(key, item, value);
		}
	}

	@Override
	public String hget(final String key, final String item) {
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.hget(key, item);
		}
	}

	@Override
	public Long hdel(final String key, final String item) {
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.hdel(key, item);
		}
	}

	@Override
	public Long incr(final String key) {
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.incr(key);
		}
	}

	@Override
	public Long decr(final String key) {
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.decr(key);
		}
	}

	@Override
	public Long expire(final String key, final int second) {
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.expire(key, second);
		}
	}

	@Override
	public Set<String> zrange(final String key, final long start, final long end) {
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.zrange(key, start, end);
		}
	}

}
