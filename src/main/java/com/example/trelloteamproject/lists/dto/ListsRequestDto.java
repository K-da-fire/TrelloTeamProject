package com.example.trelloteamproject.lists.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ListsRequestDto {
    @NotBlank(message = "설명은 필수값 입니다.")
    private String content;
    @NotNull(message = "순서는 필수값 입니다.")
    private Long orders;
}
