package com.example.trelloteamproject.board.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class BoardRequestDto {
    @NotBlank(message = "제목은 필수값 입니다.")
    private String title;
    @NotBlank(message = "배경 은 필수값 입니다.")
    private String background;
}
