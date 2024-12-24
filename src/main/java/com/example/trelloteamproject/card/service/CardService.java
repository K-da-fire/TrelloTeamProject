package com.example.trelloteamproject.card.service;

import com.example.trelloteamproject.card.dto.CardResponseDto;
import com.example.trelloteamproject.login.dto.MemberResponseDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface CardService {
    CardResponseDto create(Long id, String title, String explanation, String route, LocalDateTime deadline);

    List<CardResponseDto> searchCards(String boardName, String title, String explanation, String userName, LocalDateTime deadline);
}
