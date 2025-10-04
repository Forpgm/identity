package com.forpgm.identity_service.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name="users")

public class User {

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Id
    @UuidGenerator
    private UUID id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDob() {
        return dob;
    }
}
