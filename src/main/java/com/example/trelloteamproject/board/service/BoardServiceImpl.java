package com.example.trelloteamproject.board.service;

import com.example.trelloteamproject.board.dto.BoardResponseDto;
import com.example.trelloteamproject.board.dto.CreateBoardResponseDto;
import com.example.trelloteamproject.board.entity.Board;
import com.example.trelloteamproject.board.repository.BoardRepository;
import com.example.trelloteamproject.exception.NotFoundException;
import com.example.trelloteamproject.user.service.UserService;
import com.example.trelloteamproject.workspace.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.trelloteamproject.exception.ErrorCode.NOT_FOUND_USER;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final WorkspaceRepository workSpaceRepository;
    private final BoardRepository boardRepository;
    private final UserService userService;

    @Override
    public CreateBoardResponseDto save(String title, String background) {


//        User finduser = userService.findUserByIdOrElseThrow(user.getId());

        Board board = new Board(
                title,
                background
        );

        Board findboard = boardRepository.save(board);
        return CreateBoardResponseDto.toDto(findboard);
    }

    @Override
    public Board findBoardByIdOrElseThrow(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_USER));

    }

    @Override
    public List<BoardResponseDto> findAllBoards() {
        return boardRepository.findAll().stream().map(BoardResponseDto::toDto).toList();

    }
    @Override
    public BoardResponseDto updateWorkspace(Long board_id, String title, String background) {

        Board findBoard = boardRepository.findById(board_id).orElseThrow(() -> new NotFoundException(NOT_FOUND_USER));;

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