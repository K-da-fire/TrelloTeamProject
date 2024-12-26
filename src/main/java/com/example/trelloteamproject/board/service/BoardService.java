package com.example.trelloteamproject.board.service;


import com.example.trelloteamproject.board.dto.BoardResponseDto;
import com.example.trelloteamproject.board.dto.CreateBoardResponseDto;
import com.example.trelloteamproject.board.entity.Board;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface BoardService {
    CreateBoardResponseDto save(String title, MultipartFile background);

    Board findBoardByIdOrElseThrow(Long id);
    List<BoardResponseDto> findAllBoards();


    BoardResponseDto updateBoard(Long board_id, String title, MultipartFile background);

    void delete(Long board_id);
}
