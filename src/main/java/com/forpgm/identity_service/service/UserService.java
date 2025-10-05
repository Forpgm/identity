package com.forpgm.identity_service.service;

import com.forpgm.identity_service.dto.request.CreateUserRequest;
import com.forpgm.identity_service.dto.request.UpdateUserRequest;
import com.forpgm.identity_service.dto.response.UserResponse;
import com.forpgm.identity_service.entity.User;
import com.forpgm.identity_service.exception.AppException;
import com.forpgm.identity_service.exception.ErrorCode;
import com.forpgm.identity_service.mapper.UserMapper;
import com.forpgm.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User createRequest(CreateUserRequest request) {
        System.out.println(request.getUsername());
        if (userRepository.existsByUsername((request.getUsername()))) {
            throw new AppException(new ErrorCode(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Username already existed."));
        }
//        user.setUsername(request.getUsername());
//        user.setPassword(request.getPassword());
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setDob(request.getDob());

//        User user = User.builder().username(request.getUsername()).password(request.getPassword()).firstName(request.getFirstName()).lastName(request.getLastName()).dob(request.getDob()).build();

        User user = userMapper.toUser(request);
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserResponse getUser(String userId) {
        return userMapper.toUserResponse(userRepository.findById(UUID.fromString(userId)).orElseThrow(() -> new RuntimeException("User not found")));
    }

    public UserResponse updateUser(String userId, UpdateUserRequest request) {
//        User existingUser = getUser(userId);
//        existingUser.setPassword(request.getPassword());
//        existingUser.setFirstName(request.getFirstName());
//        existingUser.setLastName(request.getLastName());
//        existingUser.setDob(request.getDob());

        User existingUser = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new RuntimeException("User not found"));
        User updatedUser = userMapper.updateUser(existingUser, request);
        return userMapper.toUserResponse(updatedUser);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(UUID.fromString(userId));
    }
}
