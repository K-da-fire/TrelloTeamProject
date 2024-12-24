package com.example.trelloteamproject.user.controller;

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

    @DeleteMapping
    public ResponseEntity<String> deleteMember(
            @SessionAttribute(name = LoginStatus.LOGIN_USER) SessionDto session,
            @Valid @RequestBody UserDeleteRequestDto userDeleteRequestDto
            ) {
        userService.deleteMember(session.getId(), userDeleteRequestDto.getPassword());
        return ResponseEntity.ok("Member deleted.");
    }
}
