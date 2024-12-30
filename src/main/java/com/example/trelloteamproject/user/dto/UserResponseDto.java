package com.example.trelloteamproject.login.dto;

import com.example.trelloteamproject.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponseDto {

    private String email;

    private String name;

    private String token;

    public static MemberResponseDto toDto(User user, String token) {
        return new MemberResponseDto(
                user.getEmail(),
                user.getName()
        );
    }
}
