package com.example.trelloteamproject.workspace.service;

import com.example.trelloteamproject.common.Auth;

import com.example.trelloteamproject.common.Role;
import com.example.trelloteamproject.exception.NoAuthorizedException;
import com.example.trelloteamproject.exception.NotFoundException;
import com.example.trelloteamproject.invitation.entity.Invitation;
import com.example.trelloteamproject.invitation.repository.InvitationRepository;
import java.util.List;
import com.example.trelloteamproject.user.entity.User;
import com.example.trelloteamproject.user.repository.UserRepository;
import com.example.trelloteamproject.user.service.UserService;
import com.example.trelloteamproject.workspace.dto.CreateWorkspaceResponseDto;
import com.example.trelloteamproject.workspace.dto.WorkspaceResponseDto;
import com.example.trelloteamproject.workspace.entity.Workspace;
import com.example.trelloteamproject.workspace.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.trelloteamproject.exception.ErrorCode.NOT_FOUND_MEMBER;
import static com.example.trelloteamproject.exception.ErrorCode.NO_AUTHOR_CHANGE;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {

    private final WorkspaceRepository workSpaceRepository;
    private final InvitationRepository invitationRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public CreateWorkspaceResponseDto save(String title, String content) {

        User user = new User("wow@naver.com", "qqq","leehaewook",Auth.USER);

        userRepository.save(user);

        User finduser = userService.findMemberByIdOrElseThrow(user.getId());

        Workspace workSpace = new Workspace(
                title,
                content
        );
        Invitation invited = new Invitation(finduser,workSpace, Role.WORKSPACE);

        if(!invited.getRole().equals(Role.WORKSPACE)){
            throw new NoAuthorizedException(NO_AUTHOR_CHANGE);
        }
        workSpaceRepository.save(workSpace);
        invitationRepository.save(invited);
        return CreateWorkspaceResponseDto.toDto(workSpace);
    }

    @Override
    public List<WorkspaceResponseDto> findAllWorkspaces() {
        return workSpaceRepository.findAll().stream().map(WorkspaceResponseDto::toDto).toList();

    }

    @Override
    public WorkspaceResponseDto updateWorkspace(Long workspace_id, String title, String content) {

        Workspace findWorkspace = workSpaceRepository.findById(workspace_id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MEMBER));;

        findWorkspace.updateWorkspace(title,content);

        Workspace savedWorkspace = workSpaceRepository.save(findWorkspace);


        return Workspace.toDto(savedWorkspace);

    }

    @Override
    public Workspace findWorkspaceByIdOrElseThrow(Long id) {
        return workSpaceRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MEMBER));

    }

    @Override
    public void delete(Long workspace_id) {

        Workspace findWorkspace = findWorkspaceByIdOrElseThrow(workspace_id);
        workSpaceRepository.delete(findWorkspace);
    }


}
