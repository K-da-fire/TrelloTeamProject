package com.example.trelloteamproject.board.service;

import com.example.trelloteamproject.awss3.entity.AttachFile;
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
import com.example.trelloteamproject.user.entity.User;
import com.example.trelloteamproject.user.repository.UserRepository;
import com.example.trelloteamproject.user.service.UserService;
import com.example.trelloteamproject.workspace.dto.CreateWorkspaceResponseDto;
import com.example.trelloteamproject.workspace.dto.WorkspaceResponseDto;
import com.example.trelloteamproject.workspace.entity.Workspace;
import com.example.trelloteamproject.workspace.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.example.trelloteamproject.exception.ErrorCode.NOT_FOUND_MEMBER;
import static com.example.trelloteamproject.exception.ErrorCode.NO_AUTHOR_CHANGE;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final WorkspaceRepository workSpaceRepository;
    private final BoardRepository boardRepository;
    private final UserService userService;

    @Override
    public CreateBoardResponseDto save(String title, MultipartFile background) {


//        User finduser = userService.findMemberByIdOrElseThrow(user.getId());

        Board board = new Board(
                title,
                background
        );

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
    public BoardResponseDto updateBoard(Long board_id, String title, MultipartFile background) {

        Board findBoard = boardRepository.findById(board_id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MEMBER));;

        findBoard.updateBoard(title,background);

        Board savedBoard = boardRepository.save(findBoard);


        return Board.toDto(savedBoard);

    }

    @Override
    public void delete(Long board_id) {
        Board findBoard = findBoardByIdOrElseThrow(board_id);
        boardRepository.delete(findBoard);
    }
}