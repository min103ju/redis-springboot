package com.citizen.redis.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
public class User implements Serializable {

    @Getter
    private String username;

    @Getter
    @JsonProperty("create_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime createAt;

    public User(String username, LocalDateTime createAt) {
        this.username = username;
        this.createAt = createAt;
    }
}
