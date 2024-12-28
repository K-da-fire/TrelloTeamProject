package com.example.trelloteamproject.board.dto;

import com.example.trelloteamproject.board.entity.Board;
import com.example.trelloteamproject.lists.entity.Lists;
import lombok.Getter;

@Getter
public class AllBoardResponseDto {
    private Long id;
    private String title;
    private String background;

    public AllBoardResponseDto(Long id, String title, String background) {
        this.id = id;
        this.title = title;
        this.background = background;
    }

    public static AllBoardResponseDto toDto(Board board) {
        return new AllBoardResponseDto(
                board.getId(),
                board.getTitle(),
                board.getBackground().getFileName()
        );
    }
}

