package com.example.trelloteamproject.login.entity;

import com.example.trelloteamproject.common.Auth;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SessionDto {
    private Long id;

    private Auth role;
}
