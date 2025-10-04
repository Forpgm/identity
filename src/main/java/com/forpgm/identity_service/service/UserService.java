package com.forpgm.identity_service.service;

import com.forpgm.identity_service.dto.request.CreateUserRequest;
import com.forpgm.identity_service.entity.User;
import com.forpgm.identity_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createRequest(CreateUserRequest request){
        User user = new User();

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
}
