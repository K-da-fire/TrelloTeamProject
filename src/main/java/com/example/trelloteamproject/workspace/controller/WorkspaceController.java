package com.example.trelloteamproject.workspace.controller;


import com.example.trelloteamproject.common.Auth;
import com.example.trelloteamproject.login.entity.SessionDto;
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
    SessionDto session = new SessionDto(1L, Auth.ADMIN);
    @PostMapping()
    public ResponseEntity<CreateWorkspaceResponseDto> save(
            @Valid
            @RequestBody CreateWorkspaceRequestDto requestDto,
            HttpServletRequest httpServlet){
//        HttpSession session = httpServlet.getSession(false);

        Long userId = (Long) session.getId();

//        CreateWorkspaceResponseDto savedWorkspace = workspaceService.save(userId,requestDto);
        CreateWorkspaceResponseDto savedWorkspace = workspaceService.save(userId,requestDto.getTitle(),requestDto.getContent());

        return new ResponseEntity<>(savedWorkspace, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<WorkspaceResponseDto>> findAll(
            @Valid
            HttpServletRequest httpServletRequest){

        Long userId = (Long) session.getId();

        List<WorkspaceResponseDto> allWorkspaces = workspaceService.findAllWorkspaces();
        return new ResponseEntity<>(allWorkspaces,HttpStatus.OK);
    }
    @GetMapping("/users2")
    public ResponseEntity<List<WorkspaceResponseDto>> findWorkspace(
            @Valid
            HttpServletRequest httpServletRequest){

        Long userId = (Long) session.getId();

        List<WorkspaceResponseDto> findWorkspace = workspaceService.findUserAndWorkspaces(userId);
        return new ResponseEntity<>(findWorkspace,HttpStatus.OK);
    }
    @PatchMapping("/{workspaceId}")
    public ResponseEntity<WorkspaceResponseDto> update(
            @PathVariable Long workspaceId,
            @Valid
            @RequestBody WorkspaceRequestDto requestDto,
            HttpServletRequest request){

        Long userId = (Long) session.getId();
        WorkspaceResponseDto updateWorkspace = workspaceService.updateWorkspace(userId,workspaceId, requestDto.getTitle(), requestDto.getContent());

        return new ResponseEntity<>(updateWorkspace, HttpStatus.OK);
    }
    @DeleteMapping("/{workspaceId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long workspaceId,
            @Valid
            HttpServletRequest request){

        Long userId = (Long) session.getId();
        workspaceService.delete(userId,workspaceId);

        return new ResponseEntity<>(HttpStatus.OK);

    }


}
