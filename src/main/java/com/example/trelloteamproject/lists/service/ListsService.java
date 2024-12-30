package com.example.trelloteamproject.lists.service;


import com.example.trelloteamproject.board.dto.BoardResponseDto;
import com.example.trelloteamproject.board.dto.CreateBoardResponseDto;
import com.example.trelloteamproject.board.entity.Board;
import com.example.trelloteamproject.lists.dto.ListsCardResponseDto;
import com.example.trelloteamproject.lists.dto.ListsResponseDto;
import com.example.trelloteamproject.lists.entity.Lists;
import com.example.trelloteamproject.show.dto.ShowResponseDto;
import com.example.trelloteamproject.workspace.dto.WorkspaceResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ListsService {
    ListsResponseDto save(String email,Long workspaceId,Long boardId,String content, Long orders);

    Lists findListsByIdOrElseThrow(Long id);


//    List<BoardResponseDto> findAllBoards();


    List<ListsResponseDto> findBoardAndLists(Long workspaceId,Long boardId);

    List<ListsResponseDto> findOne(Long workspaceId, Long boardId, Long userId);

    List<ListsResponseDto>findAllLists();

    ListsResponseDto updateLists(String email,Long workspaceId,Long boardId,Long listsId,String content,Long orders);

    void delete(String email,Long workspaceId,Long boardId,Long listsId);
}
