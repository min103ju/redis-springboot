package com.citizen.redis.service;

import com.citizen.redis.dao.RedisDAO;
import com.citizen.redis.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final RedisDAO redisDAO;

    public User registerUser(String username) throws IOException {
        User user = new User(username, LocalDateTime.now());

        redisDAO.setUser(user);

        return redisDAO.getUser(username);
    }

    public void deleteUser(String username) {
        redisDAO.deleteUser(username);
    }

    public User getUser(String username) throws IOException {
        return redisDAO.getUser(username);
    }

    public List<String> getUsernameList() {
        return redisDAO.getAllUsers();
    }

    // 사용자의 차단 여부
    public boolean isUserBlocked(String username) {
        return redisDAO.isUserBlocked(username);
    }

    // 차단된 사용자의 남은 차단 시간 조회
    public long getUserBlockedSecondsLeft(String username) {
        return redisDAO.getUserBlockedSecondsLeft(username);
    }

    // 사용자 차단
    public void blockUser(String username) {
        redisDAO.setUserBlocked(username);
    }

    // 사용자 차단 해제
    public void unblockUser(String username) {
        redisDAO.deleteUserBlocked(username);
    }

}
