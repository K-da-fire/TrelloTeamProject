package com.example.trelloteamproject.board.service;


import com.example.trelloteamproject.board.dto.BoardResponseDto;
import com.example.trelloteamproject.board.dto.CreateBoardResponseDto;
import com.example.trelloteamproject.board.entity.Board;
import com.example.trelloteamproject.invitation.entity.Invitation;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface BoardService {
    CreateBoardResponseDto save(Long workspaceId,Long userId, String title, MultipartFile background);

    Board findBoardByIdOrElseThrow(Long id);
    List<BoardResponseDto> findAllBoards(Long userId);
    List<BoardResponseDto> findOne(Long workspaceId, Long boardId,Long userId);

    List<Board> findBoardId(Long boardId);
    BoardResponseDto updateBoard(Long userId,Long boardId, String title, MultipartFile background);

    void delete(Long userId, Long boardId);
}
