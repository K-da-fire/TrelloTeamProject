package com.example.trelloteamproject.comment.controller;

import com.example.trelloteamproject.comment.dto.CommentRequestDto;
import com.example.trelloteamproject.comment.dto.CommentResponseDto;
import com.example.trelloteamproject.comment.service.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentServiceImpl commentService;

    @PostMapping("cards/{cardId}/comments")
    public CommentResponseDto createComment(@PathVariable Long cardId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal Long userId) {

        return commentService.createComment(userId, cardId, requestDto);
    }

    // 댓글 수정
    @PatchMapping("{cardId}/comments/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long commentId, @PathVariable Long cardId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal Long userId) {

        // 댓글 수정
        return commentService.updateComment(userId, commentId, requestDto);
    }

    // 댓글 삭제
    @DeleteMapping("{cardId}/comments/{commentId}")
    public void deleteComment(@PathVariable Long commentId, @PathVariable Long cardId, @AuthenticationPrincipal Long userId) {
        // 댓글 삭제
        commentService.deleteComment(userId, commentId);
    }
}
