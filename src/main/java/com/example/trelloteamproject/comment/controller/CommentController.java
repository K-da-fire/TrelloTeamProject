package com.example.trelloteamproject.comment.controller;

import com.example.trelloteamproject.comment.dto.CommentRequestDto;
import com.example.trelloteamproject.comment.dto.CommentResponseDto;
import com.example.trelloteamproject.comment.service.CommentServiceImpl;
import com.example.trelloteamproject.login.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentServiceImpl commentService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("cards/{cardId}/comments")
    public CommentResponseDto createComment(@PathVariable Long cardId, @RequestBody CommentRequestDto requestDto, @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "").trim();
        String email = jwtTokenProvider.getUsername(token);
        return commentService.createComment(email, cardId, requestDto);
    }

    // 댓글 수정
    @PatchMapping("cards/{cardId}/comments/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long commentId, @PathVariable Long cardId, @RequestBody CommentRequestDto requestDto, @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "").trim();
        String email = jwtTokenProvider.getUsername(token);
        // 댓글 수정
        return commentService.updateComment(email, commentId, requestDto);
    }

    // 댓글 삭제
    @DeleteMapping("cards/{cardId}/comments/{commentId}")
    public void deleteComment(@PathVariable Long commentId, @PathVariable Long cardId, @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "").trim();
        String email = jwtTokenProvider.getUsername(token);
        // 댓글 삭제
        commentService.deleteComment(email, commentId);
    }
}
