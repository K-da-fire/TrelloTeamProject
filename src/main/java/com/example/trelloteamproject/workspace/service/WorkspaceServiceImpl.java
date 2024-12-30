package com.example.trelloteamproject.workspace.service;

import com.example.trelloteamproject.common.Auth;

import com.example.trelloteamproject.common.Role;
import com.example.trelloteamproject.exception.NoAuthorizedException;
import com.example.trelloteamproject.exception.NotFoundException;
import com.example.trelloteamproject.invitation.entity.Invitation;
import com.example.trelloteamproject.invitation.repository.InvitationRepository;

import java.util.ArrayList;
import java.util.List;

import com.example.trelloteamproject.invitation.service.InvitationService;
import com.example.trelloteamproject.user.entity.User;
import com.example.trelloteamproject.user.repository.UserRepository;
import com.example.trelloteamproject.user.service.UserService;
import com.example.trelloteamproject.workspace.dto.CreateWorkspaceResponseDto;
import com.example.trelloteamproject.workspace.dto.WorkspaceResponseDto;
import com.example.trelloteamproject.workspace.entity.Workspace;
import com.example.trelloteamproject.workspace.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.trelloteamproject.exception.ErrorCode.NOT_FOUND_USER;
import static com.example.trelloteamproject.exception.ErrorCode.NO_AUTHOR_CHANGE;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {

    private final WorkspaceRepository workSpaceRepository;
    private final InvitationService invitationService;
    private final UserService userService;

    @Transactional
    @Override
    public CreateWorkspaceResponseDto save(Long userId, String title, String content) {

        User finduser = userService.findUserByIdOrElseThrow(userId);

        if(!finduser.getAuth().equals(Auth.ADMIN)){
            throw new NoAuthorizedException(NO_AUTHOR_CHANGE);
        }
        Workspace workSpace = new Workspace(
                title,
                content
        );
        workSpaceRepository.save(workSpace);
        return CreateWorkspaceResponseDto.toDto(workSpace);
    }

    @Override
    public List<WorkspaceResponseDto> findAllWorkspaces() {
        return workSpaceRepository.findAll().stream().map(WorkspaceResponseDto::toDto).toList();

    }

    @Override
    public List<WorkspaceResponseDto> findUserAndWorkspaces(Long userId) {
        List<Invitation> findInvitation = invitationService.findByUserIdOrElseThrow(userId);



        List<Long> ids = findInvitation.stream().map(Invitation::getId).toList();
        return workSpaceRepository.findAllByIdIn(ids).stream().map(WorkspaceResponseDto::toDto).toList();


//        ArrayList<WorkspaceResponseDto> result = new ArrayList<>();
//
//        for (Workspace w : workspaces) {
//            WorkspaceResponseDto dto = new WorkspaceResponseDto(w.getId(), w.getTitle(), w.getContent());
//            result.add(dto);
//        }
//        return result;


    }

    @Override
    public WorkspaceResponseDto updateWorkspace(Long userId,Long workspaceId, String title, String content) {
        checkRole(userId, workspaceId);
        Workspace findWorkspace = findWorkspaceByIdOrElseThrow(workspaceId);

        findWorkspace.updateWorkspace(title,content);

        Workspace savedWorkspace = workSpaceRepository.save(findWorkspace);


        return Workspace.toDto(savedWorkspace);

    }

    @Override
    public Workspace findWorkspaceByIdOrElseThrow(Long id) {
        return workSpaceRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_USER));

    }

    @Override
    public void delete(Long userId,Long workspaceId) {
        checkRole(userId, workspaceId);
        Workspace findWorkspace = findWorkspaceByIdOrElseThrow(workspaceId);
        workSpaceRepository.delete(findWorkspace);
    }


    private void checkRole(Long userId, Long workspaceId){
        Invitation findInvitation = invitationService.findInvocationByUserAndWorkspaceIdOrElseThrow(userId, workspaceId);

        if(!findInvitation.getRole().equals(Role.WORKSPACE)){
            throw new NoAuthorizedException(NO_AUTHOR_CHANGE);
        }
    }



}
