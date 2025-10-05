package com.forpgm.identity_service.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;


import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name="users")
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @UuidGenerator
    private UUID id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;

}
