package com.example.trelloteamproject.login.service;

import com.example.trelloteamproject.common.Auth;
import com.example.trelloteamproject.user.dto.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    UserResponseDto signUp(String email, String password, String name, Auth auth);

    UserResponseDto login(String email, String password);

    // JWT 토큰을 이용한 회원 탈퇴 처리
    void deleteAccount(String token, String password);
}
