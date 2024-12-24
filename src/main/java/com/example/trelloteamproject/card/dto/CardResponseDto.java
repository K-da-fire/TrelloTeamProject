package com.example.trelloteamproject.card.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CardResponseDto {

    private String title;

    private String explanation;

    private String manager;

    private LocalDateTime deadline;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
