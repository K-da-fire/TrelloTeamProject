package com.example.trelloteamproject.invitation.service;
import com.example.trelloteamproject.common.Role;
import com.example.trelloteamproject.exception.NotFoundException;
import com.example.trelloteamproject.invitation.dto.InvitationResponseDto;
import com.example.trelloteamproject.invitation.entity.Invitation;
import com.example.trelloteamproject.invitation.repository.InvitationRepository;
import com.example.trelloteamproject.user.entity.User;
import com.example.trelloteamproject.user.repository.UserRepository;
import com.example.trelloteamproject.workspace.entity.Workspace;
import com.example.trelloteamproject.workspace.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.trelloteamproject.exception.ErrorCode.NOT_FOUND_MEMBER;

@Service
@RequiredArgsConstructor
public class InvocationServiceImpl implements InvitationService {
    private final InvitationRepository invitationRepository;
    private final UserRepository userRepository;
    private final WorkspaceRepository workspaceRepository;


    @Override
    public Role findInvocationByRoleOrElseThrow(Invitation invitation) {
        return invitationRepository.findInvitationByRole(invitation).getRole();
    }

    @Override
    public InvitationResponseDto save(String email, Long workspaceId) {
        User finduser = userRepository.findByEmail(email);

        Workspace findWorkspaceId = workspaceRepository.findById(workspaceId).orElseThrow(() -> new NotFoundException(NOT_FOUND_MEMBER));

        Invitation invitation = new Invitation(finduser,findWorkspaceId);

        Invitation savedInvitation = invitationRepository.save(invitation);

        return Invitation.toDto(savedInvitation);



    }
}
