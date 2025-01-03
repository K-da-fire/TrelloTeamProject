package com.example.trelloteamproject.invitation.service;

import com.example.trelloteamproject.common.Role;
import com.example.trelloteamproject.invitation.dto.InvitationResponseDto;
import com.example.trelloteamproject.invitation.entity.Invitation;
import com.example.trelloteamproject.workspace.dto.CreateWorkspaceResponseDto;
import org.aopalliance.intercept.Invocation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface InvitationService {
    Invitation findInvocationByUserAndWorkspaceIdOrElseThrow(String email, Long workspaceId);
    InvitationResponseDto save(String email, Long workspaceId,Role role);

    List<Invitation> findByUserIdOrElseThrow(String email);
}
