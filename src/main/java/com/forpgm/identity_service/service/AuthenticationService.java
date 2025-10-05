package com.forpgm.identity_service.service;

import com.forpgm.identity_service.dto.request.AuthenticationRequest;
import com.forpgm.identity_service.exception.AppException;
import com.forpgm.identity_service.exception.ErrorCode;
import com.forpgm.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;

    public boolean isAuthenticated(AuthenticationRequest authenticationRequest) {
        var user = userRepository.findUserByUsername(authenticationRequest.getUsername())
                .orElseThrow(() -> new AppException(
                        new ErrorCode(HttpStatus.BAD_REQUEST.value(), "User or password is invalid.")
                ));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword()));
        return passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());
    }
}
