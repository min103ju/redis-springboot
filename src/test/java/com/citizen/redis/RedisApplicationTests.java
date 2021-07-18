package com.citizen.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.assertj.core.api.Assertions.assertThat;

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

		final ValueOperations<String, String> valueOperations
				= redisTemplate.opsForValue();

		valueOperations.set(key, data);

		final String result = valueOperations.get(key);
		assertThat(result).isEqualTo(data);
	}
}
