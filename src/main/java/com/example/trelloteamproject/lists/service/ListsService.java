package com.example.trelloteamproject.lists.service;


import com.example.trelloteamproject.board.dto.BoardResponseDto;
import com.example.trelloteamproject.board.dto.CreateBoardResponseDto;
import com.example.trelloteamproject.board.entity.Board;
import com.example.trelloteamproject.lists.dto.ListsResponseDto;
import com.example.trelloteamproject.lists.entity.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ListsService {
    ListsResponseDto save(String content, Long orders);

    Lists findListsByIdOrElseThrow(Long id);


//    List<BoardResponseDto> findAllBoards();


    ListsResponseDto updateLists(Long lists_id,String content,Long orders);

    void delete(Long lists_id);
}
