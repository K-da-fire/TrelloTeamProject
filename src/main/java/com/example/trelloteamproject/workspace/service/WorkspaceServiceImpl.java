package com.example.trelloteamproject.workspace.service;

import com.example.trelloteamproject.common.Auth;

import com.example.trelloteamproject.exception.NoAuthorizedException;
import com.example.trelloteamproject.invited.entity.Invited;
import com.example.trelloteamproject.invited.repository.InvitedRepository;
import com.example.trelloteamproject.member.entity.Member;
import com.example.trelloteamproject.member.repository.MemberRepository;
import com.example.trelloteamproject.workspace.dto.CreateWorkspaceRequestDto;
import com.example.trelloteamproject.workspace.dto.CreateWorkspaceResponseDto;
import com.example.trelloteamproject.workspace.entity.Workspace;
import com.example.trelloteamproject.workspace.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.trelloteamproject.exception.ErrorCode.NO_AUTHOR_CHANGE;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {

    private final WorkspaceRepository workSpaceRepository;
    private final InvitedRepository invitedRepository;
    private final MemberRepository memberRepository;

    @Override
    public CreateWorkspaceResponseDto save(CreateWorkspaceRequestDto requestDto) {

        Member member = new Member("fsf@naver.com", "qqq","leehaewook",Auth.USER);

        memberRepository.save(member);

        Workspace workSpace = new Workspace(
                requestDto.getTitle(),
                requestDto.getContent()
        );
        Invited invited = new Invited(member,workSpace,Auth.ADMIN);

        if(!invited.getAuth().equals("ADMIN")){
            throw new NoAuthorizedException(NO_AUTHOR_CHANGE);
        }
        workSpaceRepository.save(workSpace);
        invitedRepository.save(invited);
        return CreateWorkspaceResponseDto.toDto(workSpace);
    }
}
