package com.djt.mapper.comm;

import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

public class MybatisRedisCache implements org.apache.ibatis.cache.Cache {

	// 读写锁
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

	// 这里使用了redis缓存，使用springboot自动注入
	private RedisTemplate<String, Object> redisTemplate;
	

	private String id;

	public MybatisRedisCache(final String id) {
		if (redisTemplate == null) {
			redisTemplate = (RedisTemplate<String, Object>) SpringContextUtil.getBean("redisTemplate");
		}
		if (id == null) {
			throw new IllegalArgumentException("Cache instances require an ID");
		}
		this.id = id;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void putObject(Object key, Object value) {
		if (value != null) {
			redisTemplate.opsForValue().set(key.toString(), value);
		}
	}

	@Override
	public Object getObject(Object key) {
		try {
			if (key != null) {
				Object obj = redisTemplate.opsForValue().get(key.toString());
				return obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object removeObject(Object key) {
		if (key != null) {
			redisTemplate.delete(key.toString());
		}
		return null;
	}

	@Override
	public void clear() {
		Set<String> keys = redisTemplate.keys("*:" + this.id + "*");
		if (!CollectionUtils.isEmpty(keys)) {
			redisTemplate.delete(keys);
		}
	}

	@Override
	public int getSize() {
		Long size = redisTemplate.execute((RedisCallback<Long>) RedisServerCommands::dbSize);
		return size.intValue();
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return this.readWriteLock;
	}
}
