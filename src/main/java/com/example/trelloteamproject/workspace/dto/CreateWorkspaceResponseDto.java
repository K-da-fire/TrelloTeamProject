package com.example.trelloteamproject.workspace.dto;
import com.example.trelloteamproject.workspace.entity.Workspace;
import lombok.Getter;

@Getter
public class CreateWorkspaceResponseDto {
    private String title;
    private String content;

    public CreateWorkspaceResponseDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static CreateWorkspaceResponseDto toDto(Workspace workSpace) {
        return new CreateWorkspaceResponseDto(
                workSpace.getTitle(),
                workSpace.getContent()
        );
    }
}

