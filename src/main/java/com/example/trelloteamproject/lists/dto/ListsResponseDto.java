package com.example.trelloteamproject.lists.dto;

import com.example.trelloteamproject.board.entity.Board;
import com.example.trelloteamproject.lists.entity.Lists;
import lombok.Getter;

@Getter
public class ListsResponseDto {
    private Long id;
    private String content;
    private Long order;

    public ListsResponseDto(Long id, String content, Long order) {
        this.id = id;
        this.content = content;
        this.order = order;
    }

    public static ListsResponseDto toDto(Lists lists) {
        return new ListsResponseDto(
                lists.getId(),
                lists.getContent(),
                lists.getOrders()
        );
    }
}

