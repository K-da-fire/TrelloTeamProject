package com.example.trelloteamproject.board.controller;


import com.example.trelloteamproject.board.dto.BoardResponseDto;
import com.example.trelloteamproject.board.dto.CreateBoardRequestDto;
import com.example.trelloteamproject.board.dto.CreateBoardResponseDto;
import com.example.trelloteamproject.board.service.BoardService;
import com.example.trelloteamproject.common.Auth;
import com.example.trelloteamproject.lists.dto.ListsResponseDto;
import com.example.trelloteamproject.login.entity.SessionDto;
import com.example.trelloteamproject.login.jwt.JwtTokenProvider;
import com.example.trelloteamproject.show.dto.ShowResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
//@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final JwtTokenProvider jwtTokenProvider;


    @PostMapping("/workspaces/{workspaceId}/boards")
    public ResponseEntity<CreateBoardResponseDto> save(

            @PathVariable Long workspaceId,
            @Valid
            @RequestPart CreateBoardRequestDto requestDto,
            @RequestPart(required = false) MultipartFile file,
            @RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.replace("Bearer ", "").trim();
        String email = jwtTokenProvider.getUsername(token);


        CreateBoardResponseDto savedBoard = boardService.save(email,workspaceId,requestDto.getTitle(),file);


        return new ResponseEntity<>(savedBoard, HttpStatus.CREATED);
    }

    @GetMapping("/workspaces/boards")
    public ResponseEntity<List<BoardResponseDto>> findBoards(
            @Valid
            @RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.replace("Bearer ", "").trim();
        String email = jwtTokenProvider.getUsername(token);

        List<BoardResponseDto> allBoards = boardService.findAllBoards(email);
        return new ResponseEntity<>(allBoards,HttpStatus.OK);

    }

    @GetMapping("/workspaces/{workspaceId}/boards")
    public ResponseEntity<List<BoardResponseDto>> findWorkspaceAndBoards(
            @PathVariable Long workspaceId,
            @Valid
            @RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.replace("Bearer ", "").trim();
        String email = jwtTokenProvider.getUsername(token);

        List<BoardResponseDto> findBoards = boardService.findWorkspaceAndBoards(email, workspaceId);
        return new ResponseEntity<>(findBoards,HttpStatus.OK);

    }

    @GetMapping("/workspaces/{workspaceId}/boards/{boardId}")
    public ResponseEntity<ShowResponseDto> oneBoard(
            @PathVariable Long workspaceId,
            @PathVariable Long boardId){

        ShowResponseDto allBoards = boardService.findOne(workspaceId,boardId);
        return new ResponseEntity<>(allBoards,HttpStatus.OK);

    }

    @PatchMapping("/workspaces/{workspaceId}/boards/{boardId}")
    public ResponseEntity<BoardResponseDto> update(
            @PathVariable Long workspaceId,
            @PathVariable Long boardId,
            @Valid
            @RequestPart CreateBoardRequestDto requestDto,
            @RequestPart(required = false) MultipartFile file,
            @RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.replace("Bearer ", "").trim();
        String email = jwtTokenProvider.getUsername(token);
        BoardResponseDto updateBoard = boardService.updateBoard(email,workspaceId,boardId, requestDto.getTitle(), file);

        return new ResponseEntity<>(updateBoard, HttpStatus.OK);
    }

    @DeleteMapping("/workspaces/{workspaceId}/boards/{boardId}")
    public ResponseEntity<Void> update(
            @PathVariable Long workspaceId,
            @PathVariable Long boardId,
            @Valid
            @RequestHeader("Authorization") String authorizationHeader){

        String token = authorizationHeader.replace("Bearer ", "").trim();
        String email = jwtTokenProvider.getUsername(token);
        boardService.delete(email,workspaceId,boardId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
