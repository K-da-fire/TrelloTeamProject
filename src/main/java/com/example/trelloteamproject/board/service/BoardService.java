package com.example.trelloteamproject.board.service;


import com.example.trelloteamproject.board.dto.BoardResponseDto;
import com.example.trelloteamproject.board.dto.CreateBoardResponseDto;
import com.example.trelloteamproject.board.entity.Board;
import com.example.trelloteamproject.workspace.dto.CreateWorkspaceResponseDto;
import com.example.trelloteamproject.workspace.dto.WorkspaceResponseDto;
import com.example.trelloteamproject.workspace.entity.Workspace;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardService {
    CreateBoardResponseDto save(String title, String background);

    Board findBoardByIdOrElseThrow(Long id);
    List<BoardResponseDto> findAllBoards();


    BoardResponseDto updateWorkspace(Long board_id,String title,String background);

    void delete(Long board_id);
}
