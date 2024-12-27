package com.example.trelloteamproject.workspace.dto;

import com.example.trelloteamproject.workspace.entity.Workspace;
import lombok.Getter;

@Getter
public class WorkspaceResponseDto {
    private Long id;
    private String title;
    private String content;

    public WorkspaceResponseDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public static WorkspaceResponseDto toDto(Workspace workSpace) {
        return new WorkspaceResponseDto(
                workSpace.getId(),
                workSpace.getTitle(),
                workSpace.getContent()
        );
    }
}

