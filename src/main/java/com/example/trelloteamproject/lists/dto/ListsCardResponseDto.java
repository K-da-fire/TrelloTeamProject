package com.example.trelloteamproject.lists.dto;

import com.example.trelloteamproject.card.entity.Card;
import java.util.List;

import com.example.trelloteamproject.lists.entity.Lists;
import lombok.Getter;

@Getter
public class ListsCardResponseDto {

    private Long id;
    private List<Card> cards;

    public ListsCardResponseDto(Long id, List<Card> cards){
        this.id=id;
        this.cards = cards;
    }

    public static ListsCardResponseDto toDto(Lists list){
        return new ListsCardResponseDto(
                list.getId(),
                list.getCards());
    }
}
