package com.example.trelloteamproject.card.service;

import com.example.trelloteamproject.login.dto.MemberResponseDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface CardService {
    MemberResponseDto create(Long id, String title, String explanation, String route, LocalDateTime deadline);
}
