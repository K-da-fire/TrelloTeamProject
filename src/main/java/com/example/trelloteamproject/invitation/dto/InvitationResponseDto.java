package com.example.trelloteamproject.invitation.dto;

import com.example.trelloteamproject.invitation.entity.Invitation;
import lombok.Getter;
@Getter
public class InvitationResponseDto {
    private String email;
    private Long workspaceId;

    public InvitationResponseDto(String email, Long workspaceId) {
        this.email = email;
        this.workspaceId = workspaceId;
    }
    public static InvitationResponseDto toDto(Invitation invitation) {
        return new InvitationResponseDto(
                invitation.getUser().getEmail(),
                invitation.getWorkSpace().getId()
        );
    }
}