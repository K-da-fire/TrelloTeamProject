package com.example.trelloteamproject.board.dto;

import com.example.trelloteamproject.awss3.entity.AttachFile;
import com.example.trelloteamproject.board.entity.Board;
import lombok.Getter;

@Getter
public class BoardResponseDto {
    private Long id;
    private String title;
    private String background;

    public BoardResponseDto(Long id, String title, String background) {
        this.id = id;
        this.title = title;
        this.background = background;
    }

    public static BoardResponseDto toDto(Board board) {
        return new BoardResponseDto(
                board.getId(),
                board.getTitle(),
                board.getBackground().getFileName()
        );
    }
}

