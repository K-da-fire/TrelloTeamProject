package com.example.trelloteamproject.login.service;

import com.example.trelloteamproject.common.Auth;
import com.example.trelloteamproject.exception.DuplicatedException;
import com.example.trelloteamproject.user.dto.UserResponseDto;
import com.example.trelloteamproject.user.entity.User;
import com.example.trelloteamproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.trelloteamproject.exception.ErrorCode.EMAIL_EXIST;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
}
