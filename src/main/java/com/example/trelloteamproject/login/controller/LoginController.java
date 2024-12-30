package com.example.trelloteamproject.login.controller;

import com.example.trelloteamproject.login.dto.LoginRequestDto;
import com.example.trelloteamproject.login.dto.MemberRequestDto;
import com.example.trelloteamproject.login.dto.MemberResponseDto;
import com.example.trelloteamproject.login.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<MemberResponseDto> login(
            @Valid @RequestBody LoginRequestDto userRequestDto
    ) {
        MemberResponseDto responseDto = loginService.login(
                userRequestDto.getEmail(),
                userRequestDto.getPassword()
        );
        return ResponseEntity.ok(responseDto);
    }

//    //로그아웃
//    @PostMapping("/logout")
//    public String logout(@RequestHeader("Authorization") String token) {
//        // 클라이언트 측에서 토큰을 삭제하도록
//        return "로그아웃 되었습니다";
//    }

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
