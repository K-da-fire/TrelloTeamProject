package com.example.trelloteamproject.invitation.dto;

import com.example.trelloteamproject.common.Role;
import com.example.trelloteamproject.user.entity.User;
import com.example.trelloteamproject.workspace.entity.Workspace;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class InvitationRequestDto {

    @NotBlank(message = "유저 아이디은 필수값 입니다.")
    private String email;
    @NotNull(message = "워크페이스 아이디는 필수값 입니다.")
    private Long workspaceId;
    @NotBlank(message = "역할은 필수값 입니다.")
    private String role;
}

