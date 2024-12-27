package com.example.trelloteamproject.board.dto;

import com.example.trelloteamproject.awss3.entity.AttachFile;
import com.example.trelloteamproject.board.entity.Board;
import com.example.trelloteamproject.workspace.dto.CreateWorkspaceResponseDto;
import com.example.trelloteamproject.workspace.dto.WorkspaceResponseDto;
import com.example.trelloteamproject.workspace.entity.Workspace;
import lombok.Getter;

@Getter
public class CreateBoardResponseDto {
    private Long id;
    private String title;
    private String background;


    public CreateBoardResponseDto(Long id, String title, String background) {
        this.id = id;
        this.title = title;
        this.background = background;
    }

    public static CreateBoardResponseDto toDto(Board board) {
        return new CreateBoardResponseDto(
                board.getId(),
                board.getTitle(),
                board.getBackground() == null? "": board.getBackground().getFileName()
        );
    }
}
