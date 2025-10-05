package com.forpgm.identity_service.controller;

import com.forpgm.identity_service.dto.request.ApiResponse;
import com.forpgm.identity_service.dto.request.CreateUserRequest;
import com.forpgm.identity_service.dto.request.UpdateUserRequest;
import com.forpgm.identity_service.dto.response.UserResponse;
import com.forpgm.identity_service.entity.User;
import com.forpgm.identity_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizers;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setResult(userService.createRequest(createUserRequest));
        response.setMessage("success");
        return response;
    }

    @GetMapping
    List<User> listUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    UserResponse getUser(@PathVariable("userId") String userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    ApiResponse<UserResponse> updateUser(@PathVariable("userId") String userId, @RequestBody UpdateUserRequest updateUserRequest) {
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setResult(userService.updateUser(userId, updateUserRequest));
        response.setMessage("success");
        return response;

    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
        return "User deleted successfully";
    }
}
