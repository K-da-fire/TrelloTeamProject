package com.example.trelloteamproject.lists.service;

import com.example.trelloteamproject.board.dto.BoardResponseDto;
import com.example.trelloteamproject.board.dto.CreateBoardResponseDto;
import com.example.trelloteamproject.board.entity.Board;
import com.example.trelloteamproject.board.repository.BoardRepository;
import com.example.trelloteamproject.exception.NotFoundException;
import com.example.trelloteamproject.lists.dto.ListsResponseDto;
import com.example.trelloteamproject.lists.entity.Lists;
import com.example.trelloteamproject.lists.repository.ListsRepository;
import com.example.trelloteamproject.user.service.UserService;
import com.example.trelloteamproject.workspace.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.trelloteamproject.exception.ErrorCode.NOT_FOUND_MEMBER;

@Service
@RequiredArgsConstructor
public class ListsServiceImpl implements ListsService {

    private final WorkspaceRepository workSpaceRepository;
    private final BoardRepository boardRepository;
    private final UserService userService;
    private final ListsRepository listsRepository;

    @Override
    public ListsResponseDto save(String content, Long orders) {


//        User finduser = userService.findMemberByIdOrElseThrow(user.getId());

        Lists lists = new Lists(
                content,
                orders
        );

        Lists findLists = listsRepository.save(lists);
        return ListsResponseDto.toDto(findLists);
    }

    @Override
    public Lists findListsByIdOrElseThrow(Long id) {
        return listsRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MEMBER));

    }

//    @Override
//    public List<BoardResponseDto> findAllBoards() {
//        return boardRepository.findAll().stream().map(BoardResponseDto::toDto).toList();
//
//    }


    @Override
    public ListsResponseDto updateLists(Long lists_id, String content, Long orders) {

        Lists findLists = findListsByIdOrElseThrow(lists_id);

        findLists.updateLists(content,orders);

        Lists savedLists = listsRepository.save(findLists);


        return Lists.toDto(savedLists);

    }

    @Override
    public void delete(Long lists_id) {
        Lists findLists = findListsByIdOrElseThrow(lists_id);
        listsRepository.delete(findLists);
    }
}