package com.example.trelloteamproject.lists.dto;

import com.example.trelloteamproject.card.dto.CardResponseDto;
import com.example.trelloteamproject.card.entity.Card;

import java.util.ArrayList;
import java.util.List;

import com.example.trelloteamproject.lists.entity.Lists;
import lombok.Getter;

@Getter
public class ListsCardResponseDto {

    private Long id;
    private List<CardResponseDto> cards;

    public ListsCardResponseDto(Long id, List<Card> cards){
        this.id=id;
        List<CardResponseDto> cardResponseDtos = new ArrayList<>();
        for (Card card : cards) {
            cardResponseDtos.add(new CardResponseDto(card.getTitle(), card.getExplanation(),card.getUser().getEmail(),card.getAttachFile() != null ? card.getAttachFile().getFilePath() : "",card.getDeadline(),card.getCreatedAt(),card.getUpdatedAt()));
        }
        this.cards=cardResponseDtos;

    }

    public static ListsCardResponseDto toDto(Lists list){
        return new ListsCardResponseDto(
                list.getId(),
                list.getCards());
    }
}
