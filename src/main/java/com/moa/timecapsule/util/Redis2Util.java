package com.moa.timecapsule.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Slf4j
@Component
public class Redis2Util {
	private final RedisTemplate<String, String> redisTemplateTwo;

	public Redis2Util(
		@Qualifier("redisTemplateTwo") RedisTemplate<String, String> redisTemplateTwo) {
		this.redisTemplateTwo = redisTemplateTwo;
	}

	public Long getSize(String key) {
		SetOperations<String, String> setOperations = redisTemplateTwo.opsForSet();
		return setOperations.size(key);
	}

	public void set(String key, String value, long duration) {
		ValueOperations<String, String> valueOperations = redisTemplateTwo.opsForValue();
		Duration expireDuration = Duration.ofSeconds(duration);
		valueOperations.set(key, value, expireDuration);
	}

	public void set(String key, String value) {
		SetOperations<String, String> setOperations = redisTemplateTwo.opsForSet();
		setOperations.add(key, value);
	}

	public void delete(String key, String value) {
		SetOperations<String, String> setOperations = redisTemplateTwo.opsForSet();
		setOperations.remove(key, value);
	}

	/* 타임캡슐 삭제 시 */
	public void remove(String key) {
		redisTemplateTwo.delete(key);
	}

	public void expire(String key, long duration) {
		ValueOperations<String, String> valueOperations = redisTemplateTwo.opsForValue();
		Duration expireDuration = Duration.ofSeconds(duration);
		valueOperations.getAndExpire(key, expireDuration);
	}
}
