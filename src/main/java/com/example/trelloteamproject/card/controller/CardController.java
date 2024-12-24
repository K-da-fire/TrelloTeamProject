package com.example.trelloteamproject.card.controller;

import com.example.trelloteamproject.card.dto.CardRequestDto;
import com.example.trelloteamproject.card.dto.CardResponseDto;
import com.example.trelloteamproject.card.service.CardService;
import com.example.trelloteamproject.common.LoginStatus;
import com.example.trelloteamproject.login.dto.MemberResponseDto;
import com.example.trelloteamproject.login.entity.SessionDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping
    public ResponseEntity<CardResponseDto> create(
            @SessionAttribute(name = LoginStatus.LOGIN_USER) SessionDto session,
            @Valid @RequestBody CardRequestDto cardRequestDto
    ) {
        return ResponseEntity.ok().body(cardService.create(
                session.getId(),
                cardRequestDto.getTitle(),
                cardRequestDto.getExplanation(),
                cardRequestDto.getRoute(),
                cardRequestDto.getDeadline()
        ));
    }

    // TODO : List와 Board가 추가되었을 때 boardName 혹은 boardId로 전체 보드 조회 수정
    @GetMapping
    public ResponseEntity<List<CardResponseDto>> searchCards(
            @RequestParam(required = false) String boardName,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String explanation,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) LocalDateTime deadline
    ){
        return ResponseEntity.ok().body(cardService.searchCards(boardName, title, explanation, userName, deadline));
    }
}
