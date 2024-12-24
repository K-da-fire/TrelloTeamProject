package com.example.trelloteamproject.card.controller;

import com.example.trelloteamproject.card.dto.CardCreateRequestDto;
import com.example.trelloteamproject.card.service.CardService;
import com.example.trelloteamproject.common.LoginStatus;
import com.example.trelloteamproject.login.dto.MemberRequestDto;
import com.example.trelloteamproject.login.dto.MemberResponseDto;
import com.example.trelloteamproject.login.entity.SessionDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping
    public ResponseEntity<MemberResponseDto> create(
            @SessionAttribute(name = LoginStatus.LOGIN_USER) SessionDto session,
            @Valid @RequestBody CardCreateRequestDto cardRequestDto
    ) {
        return ResponseEntity.ok().body(cardService.create(
                session.getId(),
                cardRequestDto.getTitle(),
                cardRequestDto.getExplanation(),
                cardRequestDto.getRoute(),
                cardRequestDto.getDeadline()
        ));
    }
}
