package com.example.trelloteamproject.member.controller;

import com.example.trelloteamproject.login.entity.SessionDto;
import com.example.trelloteamproject.member.dto.MemberDeleteRequestDto;
import com.example.trelloteamproject.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.trelloteamproject.common.LoginStatus;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @DeleteMapping
    public ResponseEntity<String> deleteMember(
            @SessionAttribute(name = LoginStatus.LOGIN_USER) SessionDto session,
            @Valid @RequestBody MemberDeleteRequestDto memberDeleteRequestDto
            ) {
        memberService.deleteMember(session.getId(), memberDeleteRequestDto.getPassword());
        return ResponseEntity.ok("Member deleted.");
    }
}
