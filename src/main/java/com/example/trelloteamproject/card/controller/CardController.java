package com.example.trelloteamproject.card.controller;

import com.example.trelloteamproject.card.dto.CardRequestDto;
import com.example.trelloteamproject.card.dto.CardResponseDto;
import com.example.trelloteamproject.card.service.CardService;
import com.example.trelloteamproject.login.jwt.JwtTokenProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping(value = "/workspaces/{workspaceId}/lists/{listId}/cards", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CardResponseDto> create(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable Long workspaceId,
            @PathVariable Long listId,
            @RequestPart(required = false) MultipartFile file,
            @Valid @RequestPart CardRequestDto cardRequestDto
    ) {
        String token = authorizationHeader.replace("Bearer ", "").trim();
        String email = jwtTokenProvider.getUsername(token);
        return ResponseEntity.ok().body(cardService.create(
                email,
                workspaceId,
                listId,
                cardRequestDto.getTitle(),
                cardRequestDto.getExplanation(),
                file,
                cardRequestDto.getDeadline()
        ));
    }

    @GetMapping("/cards/{id}")
    public ResponseEntity<CardResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(cardService.findById(id));
    }

    @PatchMapping(value = "/workspaces/{workspaceId}/cards/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CardResponseDto> update(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable Long workspaceId,
            @PathVariable Long id,
            @RequestPart(required = false) MultipartFile file,
            @Valid @RequestPart CardRequestDto cardRequestDto
    ){
        String token = authorizationHeader.replace("Bearer ", "").trim();
        String email = jwtTokenProvider.getUsername(token);
        return ResponseEntity.ok().body(cardService.update(
                workspaceId,
                email,
                id,
                cardRequestDto.getTitle(),
                cardRequestDto.getExplanation(),
                file,
                cardRequestDto.getDeadline()));
    }

    @DeleteMapping("/workspaces/{workspaceId}/cards/{id}")
    public ResponseEntity<String> delete(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable Long workspaceId,
            @PathVariable Long id
    ) {
        String token = authorizationHeader.replace("Bearer ", "").trim();
        String email = jwtTokenProvider.getUsername(token);
        String title = cardService.delete(workspaceId, email, id);
        return ResponseEntity.ok().body(title + "카드가 삭제 되었습니다.");
    }

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

    @GetMapping("/cards/title")
    public ResponseEntity<List<CardResponseDto>> searchCardsByTitle(
            @RequestParam String title,
            @PageableDefault Pageable pageable
    ){
        return ResponseEntity.ok().body(cardService.searchCardsByTitle(title, pageable));
    }
}
