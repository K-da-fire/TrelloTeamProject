package com.example.trelloteamproject.invitation.dto;

import com.example.trelloteamproject.common.Role;
import com.example.trelloteamproject.invitation.entity.Invitation;
import lombok.Getter;
@Getter
public class InvitationResponseDto {
    private String email;
    private Long workspaceId;
    private Role role;

    public InvitationResponseDto(String email, Long workspaceId, Role role) {
        this.email = email;
        this.workspaceId = workspaceId;
        this.role = role;
    }

    public static InvitationResponseDto toDto(Invitation invitation) {
        return new InvitationResponseDto(
                invitation.getUser().getEmail(),
                invitation.getWorkSpace().getId(),
                invitation.getRole()
        );
    }
}