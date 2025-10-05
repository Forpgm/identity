package com.forpgm.identity_service.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    @Size(min = 3, message = "Username must be at least 3 characters.")
    String username;

    @Size(min = 8, message = "Password must be at least 8 characters.")
    String password;
    String firstName;
    String lastName;
    LocalDate dob;


}
