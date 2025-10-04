package com.forpgm.identity_service.repository;

import com.forpgm.identity_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    // 1 request đi qua 3 layer: controller, service, repository
}
