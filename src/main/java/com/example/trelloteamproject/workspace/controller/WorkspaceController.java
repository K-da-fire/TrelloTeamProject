package com.example.trelloteamproject.workspace.controller;


import com.example.trelloteamproject.common.Auth;
import com.example.trelloteamproject.login.entity.SessionDto;
import com.example.trelloteamproject.login.jwt.JwtTokenProvider;
import com.example.trelloteamproject.workspace.dto.CreateWorkspaceRequestDto;
import com.example.trelloteamproject.workspace.dto.CreateWorkspaceResponseDto;
import com.example.trelloteamproject.workspace.dto.WorkspaceRequestDto;
import com.example.trelloteamproject.workspace.dto.WorkspaceResponseDto;
import com.example.trelloteamproject.workspace.entity.Workspace;
import com.example.trelloteamproject.workspace.service.WorkspaceService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workspaces")
@RequiredArgsConstructor
public class WorkspaceController {
    private final WorkspaceService workspaceService;
    private final JwtTokenProvider jwtTokenProvider;
    @PostMapping()
    public ResponseEntity<CreateWorkspaceResponseDto> save(
            @Valid
            @RequestBody CreateWorkspaceRequestDto requestDto,
            @RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.replace("Bearer ", "").trim();


        String email = jwtTokenProvider.getUsername(token);

//        CreateWorkspaceResponseDto savedWorkspace = workspaceService.save(userId,requestDto);
        CreateWorkspaceResponseDto savedWorkspace = workspaceService.save(email,requestDto.getTitle(),requestDto.getContent());

        return new ResponseEntity<>(savedWorkspace, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<WorkspaceResponseDto>> findAll(){
        List<WorkspaceResponseDto> allWorkspaces = workspaceService.findAllWorkspaces();
        return new ResponseEntity<>(allWorkspaces,HttpStatus.OK);
    }
    @GetMapping("/users2")
    public ResponseEntity<List<WorkspaceResponseDto>> findWorkspace(
            @Valid
            @RequestHeader("Authorization") String authorizationHeader){

        String token = authorizationHeader.replace("Bearer ", "").trim();
        String email = jwtTokenProvider.getUsername(token);

        List<WorkspaceResponseDto> findWorkspace = workspaceService.findUserAndWorkspaces(email);
        return new ResponseEntity<>(findWorkspace,HttpStatus.OK);
    }
    @PatchMapping("/{workspaceId}")
    public ResponseEntity<WorkspaceResponseDto> update(
            @PathVariable Long workspaceId,
            @Valid
            @RequestBody WorkspaceRequestDto requestDto,
            @RequestHeader("Authorization") String authorizationHeader){

        String token = authorizationHeader.replace("Bearer ", "").trim();
        String email = jwtTokenProvider.getUsername(token);
        WorkspaceResponseDto updateWorkspace = workspaceService.updateWorkspace(email,workspaceId, requestDto.getTitle(), requestDto.getContent());

        return new ResponseEntity<>(updateWorkspace, HttpStatus.OK);
    }
    @DeleteMapping("/{workspaceId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long workspaceId,
            @Valid
            @RequestHeader("Authorization") String authorizationHeader){

        String token = authorizationHeader.replace("Bearer ", "").trim();
        String email = jwtTokenProvider.getUsername(token);

        workspaceService.delete(email,workspaceId);

        return new ResponseEntity<>(HttpStatus.OK);

    }


}
