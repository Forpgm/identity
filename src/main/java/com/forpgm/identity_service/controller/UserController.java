package com.forpgm.identity_service.controller;

import com.forpgm.identity_service.dto.request.CreateUserRequest;
import com.forpgm.identity_service.dto.request.UpdateUserRequest;
import com.forpgm.identity_service.entity.User;
import com.forpgm.identity_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
     User createUser(@RequestBody CreateUserRequest createUserRequest) {
       return userService.createRequest(createUserRequest);
    }

    @GetMapping
    List<User> listUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    User getUser(@PathVariable("userId") String userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    User updateUser(@PathVariable("userId") String userId,@RequestBody UpdateUserRequest updateUserRequest) {
        return userService.updateUser(userId,updateUserRequest);
    }
}
