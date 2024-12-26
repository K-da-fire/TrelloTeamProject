package com.example.trelloteamproject.lists.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ListsRequestDto {
    @NotBlank(message = "제목은 필수값 입니다.")
    private String content;
    @NotBlank(message = "순서는 필수값 입니다.")
    private Long orders;
}
