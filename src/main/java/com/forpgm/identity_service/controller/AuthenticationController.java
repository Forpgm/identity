package com.forpgm.identity_service.controller;

import com.forpgm.identity_service.dto.request.ApiResponse;
import com.forpgm.identity_service.dto.request.AuthenticationRequest;
import com.forpgm.identity_service.dto.response.AuthenticationResponse;
import com.forpgm.identity_service.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest) {

        ApiResponse<AuthenticationResponse> apiResponse = new ApiResponse<>();

        var authenticationResponse = authenticationService.isAuthenticated(authenticationRequest);

            AuthenticationResponse authResponse =
                    AuthenticationResponse.builder().token(authenticationResponse.getToken()).isAuthenticated(true).build();

            apiResponse.setMessage("Authentication success");
            apiResponse.setResult(authResponse);

        return apiResponse;
    }

}
