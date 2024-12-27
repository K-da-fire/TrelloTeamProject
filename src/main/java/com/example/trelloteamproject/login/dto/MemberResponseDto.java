package com.example.trelloteamproject.login.dto;

import com.example.trelloteamproject.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponseDto {

    private String email;

    private String name;

    public static MemberResponseDto toDto(User user) {
        return new MemberResponseDto(
                user.getEmail(),
                user.getName()
        );
    }
}
