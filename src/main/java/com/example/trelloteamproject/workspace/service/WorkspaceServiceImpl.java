package com.example.trelloteamproject.workspace.service;

import com.example.trelloteamproject.common.Role;
import com.example.trelloteamproject.exception.NoAuthorizedException;
import com.example.trelloteamproject.invited.entity.Invited;
import com.example.trelloteamproject.invited.repository.InvitedRepository;
import com.example.trelloteamproject.login.repository.LoginRepository;
import com.example.trelloteamproject.member.entity.Member;
import com.example.trelloteamproject.workspace.dto.CreateWorkspaceRequestDto;
import com.example.trelloteamproject.workspace.dto.CreateWorkspaceResponseDto;
import com.example.trelloteamproject.workspace.entity.Workspace;
import com.example.trelloteamproject.workspace.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {

    private final WorkspaceRepository workSpaceRepository;
    private final InvitedRepository invitedRepository;
    private final LoginRepository loginRepository;

    @Override
    public CreateWorkspaceResponseDto save(CreateWorkspaceRequestDto requestDto) {

        Member member = new Member("fsf@naver.com", "qqq","leehaewook", Role.ADMIN);

        loginRepository.save(member);

        Workspace workSpace = new Workspace(
                requestDto.getTitle(),
                requestDto.getContent()
        );
        Invited invited = new Invited(member,workSpace,Role.ADMIN);

        if(!invited.getRole().equals("ADMIN")){
            System.out.println("권한이 없습니다.");
        }
        workSpaceRepository.save(workSpace);
        invitedRepository.save(invited);
        return CreateWorkspaceResponseDto.toDto(workSpace);
    }
}
