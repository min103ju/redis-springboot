package com.citizen.redis;

import com.citizen.redis.config.AppConfig;
import com.citizen.redis.model.Player;
import com.citizen.redis.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
public class RedisTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void redisSetOperationSortedSetTest() {
        String key = "players";

        if (redisTemplate.hasKey(key)) {
            redisTemplate.delete(key);
        }

        ZSetOperations<String, Player> zSetOperations = redisTemplate.opsForZSet();
        List<Player> list = new LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            Player player = new Player("user" + i, i);
            list.add(player);
            zSetOperations.add(key, player, player.getLevel());
        }

        Set<Player> set = zSetOperations.range(key, 0, 1000);
        assertThat(list.size()).isEqualTo(set.size());
    }

    @Test
    void redisSetOperationTests() {
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
        String setKey = "setKey";
        String str1 = "str1";
        String str2 = "str2";
        String str3 = "str3";

        setOperations.add(setKey, str1, str2, str3);

        for (String s : setOperations.members(setKey)) {
            System.out.println("s = " + s);
        }

        assertThat(setOperations.isMember(setKey, str1)).isTrue();
        assertThat(setOperations.isMember(setKey, str2)).isTrue();
        assertThat(setOperations.isMember(setKey, str3)).isTrue();

    }

    @Test
    void redisObjectTest() {
        User user = new User("user1", "pw");

        ValueOperations<String, User> valueOperations =
                redisTemplate.opsForValue();
        valueOperations.set(user.getId(), user);

        User result = valueOperations.get(user.getId());
        assertThat(result).isEqualTo(new User("user1", "pw"));
    }

    @Test
    void redisConnectionTest() {
        String key = "a";
        String data = "1";

        ValueOperations<String, String> valueOperations
                = redisTemplate.opsForValue();

        valueOperations.set(key, data);

        String result = valueOperations.get(key);
        assertThat(result).isEqualTo(data);
    }
}
