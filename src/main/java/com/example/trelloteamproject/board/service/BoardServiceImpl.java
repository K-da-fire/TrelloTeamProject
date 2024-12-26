package com.example.trelloteamproject.board.service;

import com.example.trelloteamproject.awss3.entity.AttachFile;
import com.example.trelloteamproject.awss3.service.AttachFileService;
import com.example.trelloteamproject.board.dto.BoardResponseDto;
import com.example.trelloteamproject.board.dto.CreateBoardResponseDto;
import com.example.trelloteamproject.board.entity.Board;
import com.example.trelloteamproject.board.repository.BoardRepository;
import com.example.trelloteamproject.common.Auth;
import com.example.trelloteamproject.common.Role;
import com.example.trelloteamproject.exception.NoAuthorizedException;
import com.example.trelloteamproject.exception.NotFoundException;
import com.example.trelloteamproject.invitation.entity.Invitation;
import com.example.trelloteamproject.invitation.repository.InvitationRepository;
import com.example.trelloteamproject.invitation.service.InvitationService;
import com.example.trelloteamproject.user.entity.User;
import com.example.trelloteamproject.user.repository.UserRepository;
import com.example.trelloteamproject.user.service.UserService;
import com.example.trelloteamproject.workspace.dto.CreateWorkspaceResponseDto;
import com.example.trelloteamproject.workspace.dto.WorkspaceResponseDto;
import com.example.trelloteamproject.workspace.entity.Workspace;
import com.example.trelloteamproject.workspace.repository.WorkspaceRepository;
import com.example.trelloteamproject.workspace.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.example.trelloteamproject.board.entity.QBoard.board;
import static com.example.trelloteamproject.exception.ErrorCode.NOT_FOUND_MEMBER;
import static com.example.trelloteamproject.exception.ErrorCode.NO_AUTHOR_CHANGE;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final WorkspaceService workspaceService;
    private final BoardRepository boardRepository;
    private final UserService userService;
    private final InvitationService invitationService;
    private final AttachFileService attachFileService;

    @Override
    public CreateBoardResponseDto save(Long userId, String title, MultipartFile background) {

        Workspace findWorkspace = workspaceService.findWorkspaceByIdOrElseThrow(userId);



        Long workspaceId =findWorkspace.getId();

        checkRole(userId, workspaceId);



        AttachFile attachFile = null;
        if(background != null) {
            attachFile = attachFileService.uploadFile(background);
        }

        Board board = new Board(
                title,
                attachFile

                );

        if(title==null){
            throw new NotFoundException(NOT_FOUND_MEMBER);
        }
        Board findboard = boardRepository.save(board);
        return CreateBoardResponseDto.toDto(findboard);
    }

    @Override
    public Board findBoardByIdOrElseThrow(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MEMBER));

    }

    @Override
    public List<BoardResponseDto> findAllBoards() {
        return boardRepository.findAll().stream().map(BoardResponseDto::toDto).toList();

    }
    @Override
    public BoardResponseDto updateBoard(Long userId, Long boardId, String title, MultipartFile background) {


        Workspace findWorkspace = workspaceService.findWorkspaceByIdOrElseThrow(userId);
        Long workspaceId =findWorkspace.getId();

        checkRole(userId, workspaceId);

        Board findBoard = boardRepository.findById(boardId).orElseThrow(() -> new NotFoundException(NOT_FOUND_MEMBER));;

        AttachFile fileName = findBoard.getBackground();
        if(!background.isEmpty()){
            findBoard.setBackground(null);
            boardRepository.save(findBoard);
            attachFileService.deleteFile(fileName.getFileName());
            fileName = attachFileService.uploadFile(background);
        }

        findBoard.updateBoard(title,fileName);

        if(title==null){
            throw new NotFoundException(NOT_FOUND_MEMBER);
        }
        Board savedBoard = boardRepository.save(findBoard);


        return Board.toDto(savedBoard);

    }

    @Override
    public void delete(Long userId, Long boardId) {

        Workspace findWorkspace = workspaceService.findWorkspaceByIdOrElseThrow(userId);
        Long workspaceId =findWorkspace.getId();

        checkRole(userId, workspaceId);

        Board findBoard = findBoardByIdOrElseThrow(boardId);

        AttachFile fileName = findBoard.getBackground();
        if(fileName!=null){
            findBoard.setBackground(null);
            boardRepository.save(findBoard);
            attachFileService.deleteFile(fileName.getFileName());
        }
        boardRepository.delete(findBoard);
    }

    private void checkRole(Long userId, Long workspaceId){
        Invitation findInvitation = invitationService.findInvocationByUserAndWorkspaceIdOrElseThrow(userId, workspaceId);

        if(findInvitation.getRole().equals(Role.READ_ONLY) || (findInvitation.getRole().equals(Role.BOARD))){
            throw new NoAuthorizedException(NO_AUTHOR_CHANGE);
        }
    }
}