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
import com.example.trelloteamproject.show.dto.ShowResponseDto;
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

    @PostMapping("/{boardId}/lists")
    public ResponseEntity<ListsResponseDto> save(
            @PathVariable Long boardId,
            @Valid
            @RequestBody ListsRequestDto requestDto,
            HttpServletRequest httpServlet){

        Long userId = (Long) session.getId();
//        HttpSession session = httpServlet.getSession(false);

//        Long userId = (Long) session.getAttribute("userId");

//        CreateWorkspaceResponseDto savedWorkspace = workspaceService.save(userId,requestDto);
        ListsResponseDto savedLists = listsService.save(userId,boardId,requestDto.getContent(),requestDto.getOrders());

        return new ResponseEntity<>(savedLists, HttpStatus.CREATED);
    }

    @GetMapping("/lists")
    public ResponseEntity<List<ListsResponseDto>> findList(
            @Valid
            HttpServletRequest httpServletRequest){
        List<ListsResponseDto> allLists = listsService.findAllLists();
        return new ResponseEntity<>(allLists,HttpStatus.OK);

    }

    @GetMapping("/{boardId}/lists")
    public ResponseEntity<List<ListsResponseDto>> findListB(
            @PathVariable Long boardId,
            @Valid
            HttpServletRequest httpServletRequest){
        List<ListsResponseDto> boardAndLists = listsService.findBoardAndLists(boardId);
        return new ResponseEntity<>(boardAndLists,HttpStatus.OK);

    }
//    @GetMapping("/workspaces/{workspaceId}/boards/{boardId}")
//    public ResponseEntity<List<ShowResponseDto>> oneBoard(
//            @PathVariable Long workspaceId,
//            @PathVariable Long boardId,
//
//            @Valid
//            HttpServletRequest httpServletRequest){
//        Long userId = (Long) session.getId();
//
////        List<ShowResponseDto> allBoards = boardService.findOne(workspaceId,boardId,userId);
////        return new ResponseEntity<>(allBoards,HttpStatus.OK);
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
