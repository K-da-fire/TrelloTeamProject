package com.example.trelloteamproject.card.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CardCreateRequestDto {

    private String title;

    private String explanation;

    private String route;

    private LocalDateTime deadline;
}
