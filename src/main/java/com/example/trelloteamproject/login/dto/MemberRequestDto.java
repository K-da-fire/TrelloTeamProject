package com.example.trelloteamproject.login.dto;

import com.example.trelloteamproject.common.Auth;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class MemberRequestDto {

    @NotBlank(message = "이메일은 필수값입니다.")
    @Size(max = 100, message = "이메일은 최대 100글자 입니다.")
    @Pattern(regexp = "[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z0-9.-]+$", message = "이메일 형식이 일치하지 않습니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수값입니다.")
    @Size(min = 8, max = 100, message = "비밀번호는 최소 8자, 최대 100자입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message = "비밀번호는 최소 8글자, 영어, 숫자, 특수문자가 최소 1개씩 필요합니다.")
    private String password;

    @NotBlank(message = "이름은 필수값입니다.")
    @Size(max = 10, message = "이름은 최대 10자입니다.")
    private String name;

    @NotNull(message = "권한정보는 필수입니다.")
    private Auth auth;
}
