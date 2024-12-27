package com.example.trelloteamproject.lists.dto;

import com.example.trelloteamproject.card.entity.Card;
import java.util.List;

import com.example.trelloteamproject.lists.entity.Lists;
import lombok.Getter;

@Getter
public class ListsCardResponseDto {

    private List<Card> cards;

    public ListsCardResponseDto(List<Card> cards){
        this.cards = cards;
    }

    public static ListsCardResponseDto toDto(Lists list){
        return new ListsCardResponseDto(list.getCards());
    }
}
