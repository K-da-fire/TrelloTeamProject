package com.example.trelloteamproject.invitation.service;

import com.example.trelloteamproject.common.Role;

import com.example.trelloteamproject.invitation.entity.Invitation;
import com.example.trelloteamproject.invitation.repository.InvitationRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;




@Service
@RequiredArgsConstructor
public class InvocationServiceImpl implements InvitationService {
    private final InvitationRepository invitationRepository;


    @Override
    public Role findInvocationByRoleOrElseThrow(Invitation invitation) {
        return invitationRepository.findInvitationByRole(invitation).getRole();
    }
}
