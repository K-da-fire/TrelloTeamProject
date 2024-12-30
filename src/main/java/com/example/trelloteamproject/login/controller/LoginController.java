package com.example.trelloteamproject.login.controller;


import com.example.trelloteamproject.login.service.LoginService;
import com.example.trelloteamproject.user.dto.LoginRequestDto;
import com.example.trelloteamproject.user.dto.UserResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(
            @Valid @RequestBody LoginRequestDto loginRequestDto
    ) {
        UserResponseDto responseDto = loginService.login(
                loginRequestDto.getEmail(),
                loginRequestDto.getPassword()
        );
        return ResponseEntity.ok(responseDto);
    }


    //회원 탈퇴
    @DeleteMapping("/delete")
    public String deleteAccount(@RequestHeader("Authorization") String authorizationHeader,
                                @RequestParam String password) {
        // Authorization 헤더에서 JWT 토큰 추출
        String token = authorizationHeader.replace("Bearer ", "").trim();

        // 탈퇴 처리 메서드 호출
        loginService.deleteAccount(token, password);
        return "회원 탈퇴가 완료되었습니다.";
    }
}
