package com.example.trelloteamproject.show.dto;

import com.example.trelloteamproject.card.entity.Card;
import com.example.trelloteamproject.lists.entity.Lists;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ShowRequestDto {

    @NotBlank(message = "설명은 필수값 입니다.")
    private Lists lists;
    @NotBlank(message = "순서는 필수값 입니다.")
    private Card card;
}
