package com.forpgm.identity_service.mapper;

import com.forpgm.identity_service.dto.request.CreateUserRequest;
import com.forpgm.identity_service.dto.request.UpdateUserRequest;
import com.forpgm.identity_service.dto.response.UserResponse;
import com.forpgm.identity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(CreateUserRequest createUserRequest);

    User updateUser(@MappingTarget User user, UpdateUserRequest updateUserRequest);

    // @Mapping(source = "firstName", target = "lastName")
    UserResponse toUserResponse(User user);
}
