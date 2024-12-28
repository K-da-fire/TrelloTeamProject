package com.example.trelloteamproject.card.controller;

import com.example.trelloteamproject.card.dto.CardRequestDto;
import com.example.trelloteamproject.card.dto.CardResponseDto;
import com.example.trelloteamproject.card.service.CardService;
import com.example.trelloteamproject.common.Auth;
import com.example.trelloteamproject.login.entity.SessionDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.time.LocalDateTime;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;
    private SessionDto session = new SessionDto(1L, Auth.ADMIN);

    @PostMapping(value = "/lists/{listId}/cards", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CardResponseDto> create(
//            @SessionAttribute(name = LoginStatus.LOGIN_USER) SessionDto session,
            @PathVariable Long listId,
            @RequestPart(required = false) MultipartFile file,
            @Valid @RequestPart CardRequestDto cardRequestDto
    ) {
        return ResponseEntity.ok().body(cardService.create(
                session.getId(),
                listId,
                cardRequestDto.getTitle(),
                cardRequestDto.getExplanation(),
//                cardRequestDto.getImage(),
                file,
                cardRequestDto.getDeadline()
        ));
    }

    @GetMapping("/cards/{id}")
    public ResponseEntity<CardResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(cardService.findById(id));
    }

    @PatchMapping(value = "/cards/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CardResponseDto> update(
//            @SessionAttribute(name = LoginStatus.LOGIN_USER) SessionDto session,
            @PathVariable Long id,
            @RequestPart(required = false) MultipartFile file,
            @Valid @RequestPart CardRequestDto cardRequestDto
    ){
        return ResponseEntity.ok().body(cardService.update(
                session.getId(),
                id,
                cardRequestDto.getTitle(),
                cardRequestDto.getExplanation(),
                file,
                cardRequestDto.getDeadline()
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
//            @SessionAttribute(name = LoginStatus.LOGIN_USER) SessionDto session,
            @PathVariable Long id
    ) {
        String title = cardService.delete(session.getId(), id);
        return ResponseEntity.ok().body(title + "카드가 삭제 되었습니다.");
    }

    // TODO : List와 Board가 추가되었을 때 boardName 혹은 boardId로 전체 보드 조회 수정
    @GetMapping
    public ResponseEntity<List<CardResponseDto>> searchCards(
            @RequestParam(required = false) Long boardId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String explanation,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) LocalDateTime deadline
    ){
        return ResponseEntity.ok().body(cardService.searchCards(boardId, title, explanation, userName, deadline));
    }
}
