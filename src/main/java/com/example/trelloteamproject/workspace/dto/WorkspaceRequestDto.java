package com.example.trelloteamproject.workspace.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class WorkspaceRequestDto {
    @NotBlank(message = "워크스페이스 제목은 필수값 입니다.")
    private String title;
    @NotBlank(message = "워크스페이스 은 필수값 입니다.")
    private String content;
}
