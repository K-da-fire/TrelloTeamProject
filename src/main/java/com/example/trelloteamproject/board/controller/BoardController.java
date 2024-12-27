package com.example.trelloteamproject.board.controller;


import com.example.trelloteamproject.board.dto.BoardResponseDto;
import com.example.trelloteamproject.board.dto.CreateBoardRequestDto;
import com.example.trelloteamproject.board.dto.CreateBoardResponseDto;
import com.example.trelloteamproject.board.service.BoardService;
import com.example.trelloteamproject.common.Auth;
import com.example.trelloteamproject.login.entity.SessionDto;
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

    SessionDto session = new SessionDto(1L, Auth.ADMIN);

    @PostMapping("/workspace/{workspaceId}/boards")
    public ResponseEntity<CreateBoardResponseDto> save(
            @PathVariable Long workspaceId,
            @Valid
            @RequestPart CreateBoardRequestDto requestDto,
            @RequestPart(required = false) MultipartFile file,
            HttpServletRequest httpServlet){
//        HttpSession session = httpServlet.getSession(false);

//        Long userId = (Long) session.getAttribute("userId");

        Long userId = (Long) session.getId();

        CreateBoardResponseDto savedBoard = boardService.save(workspaceId,userId,requestDto.getTitle(),file);

        return new ResponseEntity<>(savedBoard, HttpStatus.CREATED);
    }

    @GetMapping("/workspaces/{workspaceId}/boards")
    public ResponseEntity<List<BoardResponseDto>> findBoards(
            @PathVariable Long workspaceId,
            @Valid
            HttpServletRequest httpServletRequest){
        Long userId = (Long) session.getId();

        List<BoardResponseDto> allBoards = boardService.findAllBoards(userId);
        return new ResponseEntity<>(allBoards,HttpStatus.OK);

    }

    @GetMapping("/workspaces/{workspaceId}/boards/{boardId}")
    public ResponseEntity<List<BoardResponseDto>> oneBoard(
            @PathVariable Long workspaceId,
            @PathVariable Long boardId,

            @Valid
            HttpServletRequest httpServletRequest){
        Long userId = (Long) session.getId();

        List<BoardResponseDto> allBoards = boardService.findOne(workspaceId,boardId,userId);
        return new ResponseEntity<>(allBoards,HttpStatus.OK);

    }

    @PatchMapping("/boards/{boardId}")
    public ResponseEntity<BoardResponseDto> update(
            @PathVariable Long boardId,
            @Valid
            @RequestPart CreateBoardRequestDto requestDto,
            @RequestPart(required = false) MultipartFile file,
            HttpServletRequest request){
        Long userId = (Long) session.getId();
        BoardResponseDto updateBoard = boardService.updateBoard(userId,boardId, requestDto.getTitle(), file);

        return new ResponseEntity<>(updateBoard, HttpStatus.OK);
    }

    @DeleteMapping("/boards/{boardId}")
    public ResponseEntity<Void> update(
            @PathVariable Long boardId,
            @Valid
            HttpServletRequest request){

        Long userId = (Long) session.getId();
        boardService.delete(userId,boardId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
