package com.forpgm.identity_service.service;

import com.forpgm.identity_service.dto.request.AuthenticationRequest;
import com.forpgm.identity_service.dto.response.AuthenticationResponse;
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
    JwtService jwtService;

    public AuthenticationResponse isAuthenticated(AuthenticationRequest authenticationRequest) {
        var user = userRepository.findUserByUsername(authenticationRequest.getUsername())
                .orElseThrow(() -> new AppException(
                        new ErrorCode(HttpStatus.BAD_REQUEST.value(), "User or password is invalid.")
                ));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean isAuthenticated = passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());
        if (!isAuthenticated) {
            throw new AppException(new ErrorCode(HttpStatus.UNAUTHORIZED.value(), "Unathenticated"));
        }
        // sign token
        String token = jwtService.generateToken(authenticationRequest.getUsername());
        System.out.println(token);
        return AuthenticationResponse.builder().token(token).isAuthenticated(true).build();
    }

}
