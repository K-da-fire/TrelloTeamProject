package com.example.trelloteamproject.comment.service;

import com.example.trelloteamproject.card.entity.Card;
import com.example.trelloteamproject.card.service.CardService;
import com.example.trelloteamproject.comment.dto.CommentRequestDto;
import com.example.trelloteamproject.comment.dto.CommentResponseDto;
import com.example.trelloteamproject.comment.entity.Comment;
import com.example.trelloteamproject.comment.repository.CommentRepository;
import com.example.trelloteamproject.common.Auth;
import com.example.trelloteamproject.user.entity.User;
import com.example.trelloteamproject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final CardService cardService;


    @Transactional
    @Override
    public CommentResponseDto createComment(String email, Long cardId, CommentRequestDto requestDto) {
        // 유저 검증
        User user = userService.findUserByEmailOrElseThrow(email);

        // 읽기 전용 권한 확인
        if (user.getAuth() == Auth.USER) {
            throw new IllegalArgumentException("읽기 전용 권한으로 댓글을 생성할 수 없습니다.");
        }

        // 카드 검증
        Card card = cardService.findByIdOrElseThrow(cardId);

        // 댓글 생성
        Comment comment = Comment.create(requestDto.getContent(), user, card);
        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }

    // 댓글 수정
    @Transactional
    @Override
    public CommentResponseDto updateComment(String email, Long commentId, CommentRequestDto requestDto) {
        // 댓글 조회
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));

        // 작성자 확인
        if (!comment.getUser().getEmail().equals(email)) {
            throw new IllegalArgumentException("댓글 수정 권한이 없습니다.");
        }

        // 댓글 수정
        comment.updateContent(requestDto.getContent());

        return new CommentResponseDto(comment);
    }

    // 댓글 삭제
    @Transactional
    @Override
    public void deleteComment(String email, Long commentId) {
        // 댓글 조회
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));

        // 작성자 확인
        if (!comment.getUser().getEmail().equals(email)) {
            throw new IllegalArgumentException("댓글 삭제 권한이 없습니다.");
        }

        // 댓글 삭제
        commentRepository.delete(comment);
    }
}
