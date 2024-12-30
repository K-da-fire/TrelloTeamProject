package com.example.trelloteamproject.board.dto;

import com.example.trelloteamproject.awss3.entity.AttachFile;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class BoardRequestDto {
    @NotBlank(message = "제목은 필수값 입니다.")
    private String title;

}
