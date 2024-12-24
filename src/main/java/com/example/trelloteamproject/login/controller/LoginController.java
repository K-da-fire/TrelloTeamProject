package com.example.trelloteamproject.login.controller;

import com.example.trelloteamproject.login.dto.MemberRequestDto;
import com.example.trelloteamproject.login.dto.MemberResponseDto;
import com.example.trelloteamproject.login.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<MemberResponseDto> signUp(
            @Valid @RequestBody MemberRequestDto userRequestDto
    ) {
        return ResponseEntity.ok().body(loginService.signUp(userRequestDto.getEmail(), userRequestDto.getPassword(), userRequestDto.getName(), userRequestDto.getAuth()));
    }
}
