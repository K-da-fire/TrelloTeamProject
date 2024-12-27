package com.example.trelloteamproject.login.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequestDto {
    @NotBlank(message = "이메일은 필수값입니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수값입니다.")
    private String password;
}
