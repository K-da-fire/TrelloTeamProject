package com.example.trelloteamproject.lists.service;

import com.example.trelloteamproject.board.dto.BoardResponseDto;
import com.example.trelloteamproject.board.dto.CreateBoardResponseDto;
import com.example.trelloteamproject.board.entity.Board;
import com.example.trelloteamproject.board.repository.BoardRepository;
import com.example.trelloteamproject.board.service.BoardService;
import com.example.trelloteamproject.common.Role;
import com.example.trelloteamproject.exception.NoAuthorizedException;
import com.example.trelloteamproject.exception.NotFoundException;
import com.example.trelloteamproject.invitation.entity.Invitation;
import com.example.trelloteamproject.invitation.service.InvitationService;
import com.example.trelloteamproject.lists.dto.ListsResponseDto;
import com.example.trelloteamproject.lists.entity.Lists;
import com.example.trelloteamproject.lists.repository.ListsRepository;
import com.example.trelloteamproject.user.service.UserService;
import com.example.trelloteamproject.workspace.dto.WorkspaceResponseDto;
import com.example.trelloteamproject.workspace.entity.Workspace;
import com.example.trelloteamproject.workspace.repository.WorkspaceRepository;
import com.example.trelloteamproject.workspace.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.trelloteamproject.exception.ErrorCode.NOT_FOUND_MEMBER;
import static com.example.trelloteamproject.exception.ErrorCode.NO_AUTHOR_CHANGE;

@Service
@RequiredArgsConstructor
public class ListsServiceImpl implements ListsService {

    private final WorkspaceService workspaceService;
    private final ListsRepository listsRepository;
    private final InvitationService invitationService;
    private final BoardService boardService;

    @Override
    public ListsResponseDto save(Long userId,String content, Long orders) {

        Workspace findWorkspace = workspaceService.findWorkspaceByIdOrElseThrow(userId);



        Long workspaceId =findWorkspace.getId();

        checkRole(userId, workspaceId);

        Lists lists = new Lists(
                content,
                orders
        );

        Lists findLists = listsRepository.save(lists);
        return ListsResponseDto.toDto(findLists);
    }

    @Override
    public Lists findListsByIdOrElseThrow(Long id) {
        return listsRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MEMBER));

    }



    @Override
    public List<ListsResponseDto> findAllLists() {
        return listsRepository.findAll().stream().map(ListsResponseDto::toDto).toList();

    }
    @Override
    public List<ListsResponseDto> findBoardAndLists(Long boardId) {
        List<Board> findBoards = boardService.findBoardId(boardId);
        List<Long> ids = findBoards.stream().map(Board::getId).toList();

        return listsRepository.findAllByIdIn(ids).stream().map(ListsResponseDto::toDto).toList();
    }

    @Override
    public ListsResponseDto updateLists(Long userId,Long listsId, String content, Long orders) {

        Workspace findWorkspace = workspaceService.findWorkspaceByIdOrElseThrow(userId);



        Long workspaceId =findWorkspace.getId();

        checkRole(userId, workspaceId);

        Lists findLists = findListsByIdOrElseThrow(listsId);

        findLists.updateLists(content,orders);

        Lists savedLists = listsRepository.save(findLists);


        return Lists.toDto(savedLists);

    }

    @Override
    public void delete(Long userId,Long listsId) {
        Workspace findWorkspace = workspaceService.findWorkspaceByIdOrElseThrow(userId);



        Long workspaceId =findWorkspace.getId();

        checkRole(userId, workspaceId);
        Lists findLists = findListsByIdOrElseThrow(listsId);
        listsRepository.delete(findLists);
    }

    private void checkRole(Long userId, Long workspaceId){
        Invitation findInvitation = invitationService.findInvocationByUserAndWorkspaceIdOrElseThrow(userId, workspaceId);

        if(findInvitation.getRole().equals(Role.READ_ONLY)){
            throw new NoAuthorizedException(NO_AUTHOR_CHANGE);
        }
    }
}