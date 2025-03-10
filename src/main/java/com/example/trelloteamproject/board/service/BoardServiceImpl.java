package com.example.trelloteamproject.board.service;

import com.example.trelloteamproject.awss3.entity.AttachFile;
import com.example.trelloteamproject.awss3.service.AttachFileService;
import com.example.trelloteamproject.board.dto.BoardResponseDto;
import com.example.trelloteamproject.board.dto.CreateBoardResponseDto;
import com.example.trelloteamproject.board.entity.Board;
import com.example.trelloteamproject.board.repository.BoardRepository;
import com.example.trelloteamproject.common.Role;
import com.example.trelloteamproject.exception.NoAuthorizedException;
import com.example.trelloteamproject.exception.NotFoundException;
import com.example.trelloteamproject.invitation.entity.Invitation;
import com.example.trelloteamproject.invitation.service.InvitationService;
import com.example.trelloteamproject.show.dto.ShowResponseDto;
import com.example.trelloteamproject.workspace.entity.Workspace;
import com.example.trelloteamproject.workspace.service.WorkspaceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.example.trelloteamproject.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final WorkspaceService workspaceService;
    private final BoardRepository boardRepository;
    private final InvitationService invitationService;
    private final AttachFileService attachFileService;


    @Override
    public CreateBoardResponseDto save(String email,Long workspaceId, String title, MultipartFile background) {

        Workspace findWorkspace = workspaceService.findWorkspaceByIdOrElseThrow(workspaceId);



        checkRole(email, workspaceId);

        AttachFile attachFile = null;
        if(background != null) {
            attachFile = attachFileService.uploadFile(background);
        }

        Board board = new Board(title, attachFile, findWorkspace);

        if(title==null){
            throw new NotFoundException(NO_TITLE);
        }
        Board findboard = boardRepository.save(board);
        return CreateBoardResponseDto.toDto(findboard);
    }

    @Override
    public Board findBoardByIdOrElseThrow(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_BOARD));

    }

    @Override
    public List<BoardResponseDto> findAllBoards(String email) {


        return boardRepository.findAll().stream().map(BoardResponseDto::toDto).toList();

    }

    @Override
    public List<BoardResponseDto> findWorkspaceAndBoards(String email,Long workspaceId) {
        Workspace findWorkspace = workspaceService.findWorkspaceByIdOrElseThrow(workspaceId);

        Long findWorkspaceId =findWorkspace.getId();
        return boardRepository.findAllByWorkspaceId(findWorkspaceId).stream().map(BoardResponseDto::toDto).toList();

    }
    @Override
    public ShowResponseDto findOne(Long workspaceId, Long boardId) {


        return new ShowResponseDto(boardRepository.findBoardByListsAndCard(boardId));


    }

    @Override
    public List<Board> findBoardId(Long boardId) {
        return boardRepository.findBoardById(boardId);
    }


    @Override
    public BoardResponseDto updateBoard(String email, Long workspaceId, Long boardId, String title, MultipartFile background) {



        checkRole(email, workspaceId);

        Board findBoard = boardRepository.findById(boardId).orElseThrow(() -> new NotFoundException(NOT_FOUND_BOARD));;

        AttachFile fileName = findBoard.getBackground();
        if(!background.isEmpty()){
            findBoard.setBackground(null);
            boardRepository.save(findBoard);
            attachFileService.deleteFile(fileName.getFileName());
            fileName = attachFileService.uploadFile(background);
        }

        findBoard.updateBoard(title,fileName);

        if(title==null){
            throw new NotFoundException(NO_TITLE);
        }
        Board savedBoard = boardRepository.save(findBoard);


        return Board.toDto(savedBoard);

    }
    @Transactional
    @Override
    public void delete(String email, Long workspaceId,Long boardId) {



        checkRole(email, workspaceId);

        Board findBoard = findBoardByIdOrElseThrow(boardId);

        AttachFile fileName = findBoard.getBackground();
        if(fileName!=null){
            findBoard.setBackground(null);
            boardRepository.save(findBoard);
            attachFileService.deleteFile(fileName.getFileName());
        }
        boardRepository.delete(findBoard);
    }

    private void checkRole(String email, Long workspaceId){
        Invitation findInvitation = invitationService.findInvocationByUserAndWorkspaceIdOrElseThrow(email, workspaceId);

        if(findInvitation.getRole().equals(Role.READ_ONLY)){
            throw new NoAuthorizedException(NO_AUTHOR_CHANGE);
        }
    }
}