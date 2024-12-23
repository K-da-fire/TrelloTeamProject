package com.example.trelloteamproject.login.service;

import com.example.trelloteamproject.login.dto.MemberRequestDto;
import com.example.trelloteamproject.login.dto.MemberResponseDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    MemberResponseDto signUp(@Valid MemberRequestDto userRequestDto);
}
