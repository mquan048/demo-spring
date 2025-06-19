package com.api.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.api.demo.dto.request.UserCreateRequest;
import com.api.demo.dto.request.UserUpdateRequest;
import com.api.demo.dto.response.UserResponse;
import com.api.demo.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreateRequest request);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);

    UserResponse toUserResponse(User user);
}
