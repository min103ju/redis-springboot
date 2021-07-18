package com.citizen.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisApplicationTests {

	@Autowired
	RedisTemplate redisTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	void redisConnectionTest() {
		final String key = "a";
		final String data = "1";
	}
}
