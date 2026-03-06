package com.example.stayease.mapper;

import com.example.stayease.dto.userDto.UserRequest;
import com.example.stayease.dto.userDto.UserResponse;
import com.example.stayease.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserRequest request){
            return User.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .password(request.getPassword())
                    .role(request.getRole())
                    .build();
    }

    public UserResponse toResponse(User user){
            return UserResponse.builder()
                    .id(user.getId())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .role(user.getRole())
                    .build();

    }
}
