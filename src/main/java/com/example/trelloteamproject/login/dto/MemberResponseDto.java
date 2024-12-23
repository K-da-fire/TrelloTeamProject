package com.example.trelloteamproject.login.dto;

import com.example.trelloteamproject.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponseDto {

    private String email;

    private String name;

    public static MemberResponseDto toDto(Member member) {
        return new MemberResponseDto(
                member.getEmail(),
                member.getName()
        );
    }
}
