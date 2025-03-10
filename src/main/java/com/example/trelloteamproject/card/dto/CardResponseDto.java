package com.example.trelloteamproject.card.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CardResponseDto {

    private String title;

    private String explanation;

    private String manager;

    private String filePath;

    private LocalDateTime deadline;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
