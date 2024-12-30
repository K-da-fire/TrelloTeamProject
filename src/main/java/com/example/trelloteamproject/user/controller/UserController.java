package com.example.trelloteamproject.user.controller;

import com.example.trelloteamproject.user.dto.UserSignInRequestDto;
import com.example.trelloteamproject.user.dto.UserResponseDto;
import com.example.trelloteamproject.login.entity.SessionDto;
import com.example.trelloteamproject.user.dto.UserDeleteRequestDto;
import com.example.trelloteamproject.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.trelloteamproject.common.LoginStatus;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> signUp(
            @Valid @RequestBody UserSignInRequestDto userSignInRequestDto
    ) {
        return ResponseEntity.ok().body(userService.signUp(userSignInRequestDto.getEmail(), userSignInRequestDto.getPassword(), userSignInRequestDto.getName(), userSignInRequestDto.getAuth()));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(
            @SessionAttribute(name = LoginStatus.LOGIN_USER) SessionDto session,
            @Valid @RequestBody UserDeleteRequestDto userDeleteRequestDto
            ) {
        userService.deleteUser(session.getId(), userDeleteRequestDto.getPassword());
        return ResponseEntity.ok("User deleted.");
    }
}
