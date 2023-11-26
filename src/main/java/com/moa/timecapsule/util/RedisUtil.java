package com.moa.timecapsule.util;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RedisUtil {
	private final RedisTemplate<String, String> redisTemplate;

	public String getData(String key) {
		ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
		return valueOperations.get(key);
	}

	public void setData(String key, String value) {
		ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
		valueOperations.set(key, value);
	}

	public void setDataExpire(String key, String value, long duration) {
		ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
		Duration expireDuration = Duration.ofSeconds(duration);
		valueOperations.set(key, value, expireDuration);
	}

	// 타임캡슐 삭제 시 사용
	public void delete(String key) {
		redisTemplate.delete(key);
	}

	// set
	public Long getSize(String key) {
		SetOperations<String, String> setOperations = redisTemplate.opsForSet();
		Set<String> keySet = setOperations.members(key);
		if (keySet != null) {
			keySet.removeIf(itKey -> getData(itKey) == null);
		}
		return setOperations.size(key);
	}

	public void set(String key, String value) {
		SetOperations<String, String> setOperations = redisTemplate.opsForSet();
		setOperations.add(key, value);
	}

	public void delete(String key, String value) {
		SetOperations<String, String> setOperations = redisTemplate.opsForSet();
		setOperations.remove(key, value);
	}
}
