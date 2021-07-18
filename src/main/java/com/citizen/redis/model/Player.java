package com.citizen.redis.model;

import java.io.Serializable;

public class Player implements Serializable {
    private final String name;
    private final int level;

    public Player(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }
}
