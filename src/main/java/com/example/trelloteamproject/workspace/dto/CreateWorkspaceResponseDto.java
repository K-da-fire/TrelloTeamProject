package com.example.trelloteamproject.workspace.dto;
import com.example.trelloteamproject.workspace.entity.Workspace;
import lombok.Getter;

@Getter
public class CreateWorkspaceResponseDto {
    private Long id;
    private String title;
    private String content;

    public CreateWorkspaceResponseDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public static CreateWorkspaceResponseDto toDto(Workspace workSpace) {
        return new CreateWorkspaceResponseDto(
                workSpace.getId(),
                workSpace.getTitle(),
                workSpace.getContent()
        );
    }
}

