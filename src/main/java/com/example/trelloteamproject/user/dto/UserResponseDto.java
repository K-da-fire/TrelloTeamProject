package com.example.trelloteamproject.user.dto;

import com.example.trelloteamproject.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDto {

    private String email;

    private String name;

    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(
                user.getEmail(),
                user.getName()
        );
    }
}
