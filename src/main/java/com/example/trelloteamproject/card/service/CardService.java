package com.example.trelloteamproject.card.service;

import com.example.trelloteamproject.card.dto.CardResponseDto;
import com.example.trelloteamproject.card.entity.Card;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface CardService {
    CardResponseDto create(Long id, String title, String explanation, MultipartFile image, LocalDateTime deadline);

    List<CardResponseDto> searchCards(String boardName, String title, String explanation, String userName, LocalDateTime deadline);

    CardResponseDto findById(Long id);

    Card findByIdOrElseThrow(Long id);

    String delete(Long userId, Long cardId);

    CardResponseDto update(Long userId, Long cardId, String title, String explanation, MultipartFile image, LocalDateTime deadline);

    List<CardResponseDto> findByListId(Long listId);
}
