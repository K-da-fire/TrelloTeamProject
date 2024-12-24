package com.example.trelloteamproject.invitation.service;

import com.example.trelloteamproject.common.Role;
import com.example.trelloteamproject.invitation.dto.InvitationResponseDto;
import com.example.trelloteamproject.invitation.entity.Invitation;
import com.example.trelloteamproject.workspace.dto.CreateWorkspaceResponseDto;
import org.aopalliance.intercept.Invocation;
import org.springframework.stereotype.Service;

@Service
public interface InvitationService {
    Role findInvocationByRoleOrElseThrow(Invitation invitation);
    InvitationResponseDto save(String email, Long workspaceId);
}
