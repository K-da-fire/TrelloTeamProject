package com.example.trelloteamproject.user.service;

import com.example.trelloteamproject.common.Auth;
import com.example.trelloteamproject.user.dto.UserResponseDto;
import com.example.trelloteamproject.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserResponseDto signUp(String email, String password, String name, Auth auth);

    User findUserByIdOrElseThrow(Long id);

    User findUserByEmailOrElseThrow(String email);
}
