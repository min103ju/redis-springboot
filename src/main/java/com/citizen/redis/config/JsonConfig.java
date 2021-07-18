package com.citizen.redis.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

// Response를 정상적으로 Json으로 응답해주기 위한 Configuration
@Configuration
public class JsonConfig {

    // ObjectMapper를 스프링 부트에서 DI로 사용할 수 있도록 설정
    @Bean
    public ObjectMapper objectMapper() {
        return Jackson2ObjectMapperBuilder.json()
                // Serialize 할 객체가 비어있으면 실패하는 기능
                .featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                // timestamp로 작성하는 기능을 비활성화
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                // JavaTimeModule을 활성화
                .modules(new JavaTimeModule())
                .build();
    }
}
