package com.citizen.redis.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class User implements Serializable {
    private String id;
    private String pw;

    public User(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(pw, user.pw);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pw);
    }
}
