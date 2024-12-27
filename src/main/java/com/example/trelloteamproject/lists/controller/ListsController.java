package com.example.trelloteamproject.lists.controller;


import com.example.trelloteamproject.board.dto.BoardResponseDto;
import com.example.trelloteamproject.board.dto.CreateBoardRequestDto;
import com.example.trelloteamproject.board.dto.CreateBoardResponseDto;
import com.example.trelloteamproject.board.service.BoardService;
import com.example.trelloteamproject.common.Auth;
import com.example.trelloteamproject.lists.dto.ListsRequestDto;
import com.example.trelloteamproject.lists.dto.ListsResponseDto;
import com.example.trelloteamproject.lists.service.ListsService;
import com.example.trelloteamproject.login.entity.SessionDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/boards")
@RequiredArgsConstructor
public class ListsController {
    private final ListsService listsService;

    SessionDto session = new SessionDto(1L, Auth.ADMIN);

    @PostMapping("/lists")
    public ResponseEntity<ListsResponseDto> save(
            @Valid
            @RequestBody ListsRequestDto requestDto,
            HttpServletRequest httpServlet){

        Long userId = (Long) session.getId();
//        HttpSession session = httpServlet.getSession(false);

//        Long userId = (Long) session.getAttribute("userId");

//        CreateWorkspaceResponseDto savedWorkspace = workspaceService.save(userId,requestDto);
        ListsResponseDto savedLists = listsService.save(userId,requestDto.getContent(),requestDto.getOrders());

        return new ResponseEntity<>(savedLists, HttpStatus.CREATED);
    }

//    @GetMapping("/workspaces/{workspace_id}/boards")
//    public ResponseEntity<List<BoardResponseDto>> findBoard(
//            @PathVariable Long workspace_id,
//            @Valid
//            HttpServletRequest httpServletRequest){
//        List<BoardResponseDto> allBoards = boardService.findAllBoards();
//        return new ResponseEntity<>(allBoards,HttpStatus.OK);
//
//    }

    @PatchMapping("/lists/{listsId}")
    public ResponseEntity<ListsResponseDto> update(
            @PathVariable Long listsId,
            @Valid
            @RequestBody ListsRequestDto requestDto,
            HttpServletRequest request){
        Long userId = (Long) session.getId();

        ListsResponseDto updateWorkspace = listsService.updateLists(userId,listsId, requestDto.getContent(), requestDto.getOrders());

        return new ResponseEntity<>(updateWorkspace, HttpStatus.OK);
    }

    @DeleteMapping("/lists/{listsId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long listsId,
            @Valid
            HttpServletRequest request){
        Long userId = (Long) session.getId();
        listsService.delete(userId,listsId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
