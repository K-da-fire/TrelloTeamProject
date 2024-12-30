package com.example.trelloteamproject.comment.service;

import com.example.trelloteamproject.comment.dto.CommentRequestDto;
import com.example.trelloteamproject.comment.dto.CommentResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface CommentService {

    // 댓글 생성
    @Transactional
    CommentResponseDto createComment(String email, Long cardId, CommentRequestDto requestDto);

    // 댓글 수정
    @Transactional
    CommentResponseDto updateComment(String email, Long commentId, CommentRequestDto requestDto);

    // 댓글 삭제
    @Transactional
    void deleteComment(String email, Long commentId);
}
