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
    private List<ListsCardResponseDto> lists;  // 보드 안엥 있는 리스트


    public ShowResponseDto(Board boardByListsAndCard) {
        this.id=boardByListsAndCard.getId();
        this.lists=boardByListsAndCard.getLists().stream().map(ListsCardResponseDto::toDto).toList();
    }


}
