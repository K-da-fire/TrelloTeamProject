package com.example.trelloteamproject.card.service;

import com.example.trelloteamproject.card.dto.CardResponseDto;
import com.example.trelloteamproject.card.entity.Card;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface CardService {
    CardResponseDto create(String userId, Long listId, String title, String explanation, MultipartFile image, LocalDateTime deadline);

    List<CardResponseDto> searchCards(Long boardId, String title, String explanation, String userName, LocalDateTime deadline);

    CardResponseDto findById(Long id);

    Card findByIdOrElseThrow(Long id);

    String delete(String userId, Long cardId);

    CardResponseDto update(String userId, Long cardId, String title, String explanation, MultipartFile image, LocalDateTime deadline);

    List<CardResponseDto> findByListId(Long listId);

    List<CardResponseDto> searchCardsByTitle(String title, Pageable pageable);
}
