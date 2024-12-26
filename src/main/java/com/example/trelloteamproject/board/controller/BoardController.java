package com.example.trelloteamproject.board.controller;


import com.example.trelloteamproject.board.dto.BoardResponseDto;
import com.example.trelloteamproject.board.dto.CreateBoardRequestDto;
import com.example.trelloteamproject.board.dto.CreateBoardResponseDto;
import com.example.trelloteamproject.board.service.BoardService;
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

    @PostMapping("/boards")
    public ResponseEntity<CreateBoardResponseDto> save(
            @Valid
            @RequestPart CreateBoardRequestDto requestDto,
            @RequestPart(required = false) MultipartFile file,
            HttpServletRequest httpServlet){
//        HttpSession session = httpServlet.getSession(false);

//        Long userId = (Long) session.getAttribute("userId");

//        CreateWorkspaceResponseDto savedWorkspace = workspaceService.save(userId,requestDto);
        CreateBoardResponseDto savedBoard = boardService.save(requestDto.getTitle(),file);

        return new ResponseEntity<>(savedBoard, HttpStatus.CREATED);
    }

    @GetMapping("/workspaces/{workspace_id}/boards")
    public ResponseEntity<List<BoardResponseDto>> findBoard(
            @PathVariable Long workspace_id,
            @Valid
            HttpServletRequest httpServletRequest){
        List<BoardResponseDto> allBoards = boardService.findAllBoards();
        return new ResponseEntity<>(allBoards,HttpStatus.OK);

    }

    @PatchMapping("/boards/{board_id}")
    public ResponseEntity<BoardResponseDto> update(
            @PathVariable Long board_id,
            @Valid
            @RequestPart CreateBoardRequestDto requestDto,
            @RequestPart(required = false) MultipartFile file,
            HttpServletRequest request){
        BoardResponseDto updateBoard = boardService.updateBoard(board_id, requestDto.getTitle(), file);

        return new ResponseEntity<>(updateBoard, HttpStatus.OK);
    }

    @DeleteMapping("/boards/{board_id}")
    public ResponseEntity<Void> update(
            @PathVariable Long board_id,
            @Valid
            HttpServletRequest request){

        boardService.delete(board_id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
