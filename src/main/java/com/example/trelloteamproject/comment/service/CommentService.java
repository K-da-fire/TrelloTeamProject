package com.example.trelloteamproject.comment.service;

import com.example.trelloteamproject.comment.dto.CommentRequestDto;
import com.example.trelloteamproject.comment.dto.CommentResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface CommentService {

    // 댓글 생성
    @Transactional
    CommentResponseDto createComment(Long userId, Long cardId, CommentRequestDto requestDto);

    // 댓글 수정
    @Transactional
    CommentResponseDto updateComment(Long userId, Long commentId, CommentRequestDto requestDto);

    // 댓글 삭제
    @Transactional
    void deleteComment(Long userId, Long commentId);
}
