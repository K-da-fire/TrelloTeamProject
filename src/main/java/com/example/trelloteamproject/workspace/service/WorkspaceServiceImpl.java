package com.example.trelloteamproject.workspace.service;

import com.example.trelloteamproject.common.Auth;

import com.example.trelloteamproject.common.Role;
import com.example.trelloteamproject.exception.NoAuthorizedException;
import com.example.trelloteamproject.invited.entity.invitation;
import com.example.trelloteamproject.invited.repository.invitationRepository;
import com.example.trelloteamproject.member.entity.User;
import com.example.trelloteamproject.member.repository.UserRepository;
import com.example.trelloteamproject.member.service.UserService;
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
    private final invitationRepository invitationRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public CreateWorkspaceResponseDto save(CreateWorkspaceRequestDto requestDto) {

        User user = new User("fsf@naver.com", "qqq","leehaewook",Auth.USER);

        userRepository.save(user);

        User finduser = userService.findMemberByIdOrElseThrow(user.getId());

        Workspace workSpace = new Workspace(
                requestDto.getTitle(),
                requestDto.getContent()
        );
        invitation invited = new invitation(finduser,workSpace, Role.WORKSPACE);

        if(!invited.getRole().equals(Role.WORKSPACE)){
            throw new NoAuthorizedException(NO_AUTHOR_CHANGE);
        }
        workSpaceRepository.save(workSpace);
        invitationRepository.save(invited);
        return CreateWorkspaceResponseDto.toDto(workSpace);
    }
}
