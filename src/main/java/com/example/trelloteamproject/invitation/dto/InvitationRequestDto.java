package com.example.trelloteamproject.invitation.dto;

import com.example.trelloteamproject.member.entity.User;
import com.example.trelloteamproject.workspace.entity.Workspace;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class InvitationRequestDto {
    @NotBlank(message = "유저 아이디은 필수값 입니다.")
    private User user;
    @NotBlank(message = "워크페이스 아이디는 필수값 입니다.")
    private Workspace workspaceId;
}

