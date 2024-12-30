package com.example.trelloteamproject.comment.repository;

import com.example.trelloteamproject.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 댓글 작성자와 카드 ID로 댓글을 조회
    Comment findByIdAndUserId(Long commentId, Long userId);
}

