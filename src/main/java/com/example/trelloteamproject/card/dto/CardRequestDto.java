package com.example.trelloteamproject.card.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
public class CardRequestDto {

    private String title;

    private String explanation;

    private MultipartFile image;

    private LocalDateTime deadline;
}
