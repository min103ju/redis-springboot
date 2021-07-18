package com.citizen.redis.controller;

import com.citizen.redis.controller.request.RegisterUserRequest;
import com.citizen.redis.domain.User;
import com.citizen.redis.service.UserService;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/redis-sample/v1")
@RestController
public class RedisSampleController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity getAllUsers() {
        List<String> users = userService.getUsernameList();
        return new ResponseEntity(ImmutableMap.of("users", users),
                HttpStatus.OK);
    }

    @GetMapping("/users/{username}")
    public ResponseEntity getUser(
        @PathVariable("username") String username
    ) throws IOException {
        User user = userService.getUser(username);

        if (user == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity registerUser(
        @RequestBody RegisterUserRequest request
    ) throws IOException {
        User user = userService.registerUser(request.getUsername());

        return new ResponseEntity(user, HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{username}")
    public ResponseEntity deleteUser(
        @PathVariable("username") String username
    ) {
        userService.deleteUser(username);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/blocked-users/{username}")
    public ResponseEntity isUserBlocked(
        @PathVariable("username") String username
    ) throws IOException {
        boolean blocked = userService.isUserBlocked(username);

        if (!blocked) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        long secondsLeft = userService.getUserBlockedSecondsLeft(username);

        return new ResponseEntity(
                ImmutableMap.of("unblock_after_seconds", secondsLeft),
                HttpStatus.OK
        );
    }

    @PostMapping("/blocked-users/{username}")
    public ResponseEntity blockUser(
        @PathVariable("username") String username
    ) {
        userService.blockUser(username);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/blocked-users/{username}")
    public ResponseEntity unblockUser(
        @PathVariable("username") String username
    ) {
        userService.unblockUser(username);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
