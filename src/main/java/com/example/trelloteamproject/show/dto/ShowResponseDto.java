package com.example.trelloteamproject.show.dto;

import com.example.trelloteamproject.board.entity.Board;
import com.example.trelloteamproject.card.entity.Card;
import com.example.trelloteamproject.lists.dto.ListsCardResponseDto;
import com.example.trelloteamproject.lists.dto.ListsResponseDto;
import com.example.trelloteamproject.lists.entity.Lists;
import lombok.Getter;

import java.util.List;

@Getter
public class ShowResponseDto {
    private Long id;    // 보드 아이디
    private List<Lists> lists;  // 보드 안엥 있는 리스트
    private List<ListsCardResponseDto> cards;

    public ShowResponseDto(Long id, List<Lists> lists, List<ListsCardResponseDto> card) {
        this.id = id;
        this.lists = lists;
        this.cards = card;
    }

    public static ShowResponseDto toDto(Board board) {
        return new ShowResponseDto(
                board.getId(),
                board.getLists(),
                board.getLists().stream().map(list -> {
                    return new ListsCardResponseDto(list.getCards());
                }).toList()
        );
    }

}
