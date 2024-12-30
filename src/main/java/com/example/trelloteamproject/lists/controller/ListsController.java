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
import com.example.trelloteamproject.login.jwt.JwtTokenProvider;
import com.example.trelloteamproject.show.dto.ShowResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/boards")
@RequiredArgsConstructor
public class ListsController {
    private final ListsService listsService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/workspace/{workspaceId}/{boardId}/lists")
    public ResponseEntity<ListsResponseDto> save(
            @PathVariable Long workspaceId,
            @PathVariable Long boardId,
            @Valid
            @RequestBody ListsRequestDto requestDto,
            @RequestHeader("Authorization") String authorizationHeader){

        String token = authorizationHeader.replace("Bearer ", "").trim();
        String email = jwtTokenProvider.getUsername(token);

        ListsResponseDto savedLists = listsService.save(email,workspaceId,boardId,requestDto.getContent(),requestDto.getOrders());

        return new ResponseEntity<>(savedLists, HttpStatus.CREATED);
    }

    @GetMapping("/lists")
    public ResponseEntity<List<ListsResponseDto>> findList(){
        List<ListsResponseDto> allLists = listsService.findAllLists();
        return new ResponseEntity<>(allLists,HttpStatus.OK);

    }

    @GetMapping("/workspace/{workspaceId}/{boardId}/lists")
    public ResponseEntity<List<ListsResponseDto>> findListPath(
            @PathVariable Long workspaceId,
            @PathVariable Long boardId){
        List<ListsResponseDto> boardAndLists = listsService.findBoardAndLists(workspaceId,boardId);
        return new ResponseEntity<>(boardAndLists,HttpStatus.OK);

    }


    @PatchMapping("/workspace/{workspaceId}/{boardId}/lists/{listsId}")
    public ResponseEntity<ListsResponseDto> update(
            @PathVariable Long workspaceId,
            @PathVariable Long boardId,
            @PathVariable Long listsId,
            @Valid
            @RequestBody ListsRequestDto requestDto,
            @RequestHeader("Authorization") String authorizationHeader){

        String token = authorizationHeader.replace("Bearer ", "").trim();
        String email = jwtTokenProvider.getUsername(token);


        ListsResponseDto updateWorkspace = listsService.updateLists(email,workspaceId,boardId,listsId, requestDto.getContent(), requestDto.getOrders());

        return new ResponseEntity<>(updateWorkspace, HttpStatus.OK);
    }

    @DeleteMapping("/workspace/{workspaceId}/{boardId}/lists/{listsId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long workspaceId,
            @PathVariable Long boardId,
            @PathVariable Long listsId,
            @Valid
            @RequestHeader("Authorization") String authorizationHeader){


        String token = authorizationHeader.replace("Bearer ", "").trim();
        String email = jwtTokenProvider.getUsername(token);
        listsService.delete(email,workspaceId,boardId,listsId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
