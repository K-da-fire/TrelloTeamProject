package com.example.trelloteamproject.board.service;


import com.example.trelloteamproject.board.dto.BoardResponseDto;
import com.example.trelloteamproject.board.dto.CreateBoardResponseDto;
import com.example.trelloteamproject.board.entity.Board;
import com.example.trelloteamproject.invitation.entity.Invitation;
import com.example.trelloteamproject.lists.dto.ListsResponseDto;
import com.example.trelloteamproject.show.dto.ShowResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface BoardService {
    CreateBoardResponseDto save(String email,Long workspaceId, String title, MultipartFile background);

    Board findBoardByIdOrElseThrow(Long id);
    List<BoardResponseDto> findAllBoards(String email);
    ShowResponseDto findOne(Long workspaceId, Long boardId);
    List<BoardResponseDto> findWorkspaceAndBoards(String email,Long workspaceId);
    List<Board> findBoardId(Long boardId);
    BoardResponseDto updateBoard(String email,Long workspaceId,Long boardId, String title, MultipartFile background);

    void delete(String email,Long workspaceId, Long boardId);
}
