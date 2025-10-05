package com.forpgm.identity_service.service;

import com.forpgm.identity_service.dto.request.CreateUserRequest;
import com.forpgm.identity_service.dto.request.UpdateUserRequest;
import com.forpgm.identity_service.entity.User;
import com.forpgm.identity_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createRequest(CreateUserRequest request){
        User user = new User();
if (userRepository.existsByUsername((request.getUsername()))){
    throw new RuntimeException("User already exists");
}
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        return userRepository.save(user);
    }

   public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUser(String userId){
        return userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(String userId, UpdateUserRequest request) {
        User existingUser = getUser(userId);

        existingUser.setPassword(request.getPassword());
        existingUser.setFirstName(request.getFirstName());
        existingUser.setLastName(request.getLastName());
        existingUser.setDob(request.getDob());
            return userRepository.save(existingUser);
        }

        public void deleteUser(String userId){
        userRepository.deleteById(UUID.fromString(userId));
        }
}
