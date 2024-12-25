package com.example.trelloteamproject.workspace.controller;


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

    @PostMapping()
    public ResponseEntity<CreateWorkspaceResponseDto> save(
            @Valid
            @RequestBody CreateWorkspaceRequestDto requestDto,
            HttpServletRequest httpServlet){
//        HttpSession session = httpServlet.getSession(false);

//        Long userId = (Long) session.getAttribute("userId");

//        CreateWorkspaceResponseDto savedWorkspace = workspaceService.save(userId,requestDto);
        CreateWorkspaceResponseDto savedWorkspace = workspaceService.save(requestDto.getTitle(),requestDto.getContent());

        return new ResponseEntity<>(savedWorkspace, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<WorkspaceResponseDto>> findAll(
            @Valid
            HttpServletRequest httpServletRequest){
        List<WorkspaceResponseDto> allWorkspaces = workspaceService.findAllWorkspaces();
        return new ResponseEntity<>(allWorkspaces,HttpStatus.OK);
    }
    @PatchMapping("{workspace_id}")
    public ResponseEntity<WorkspaceResponseDto> update(
            @PathVariable Long workspace_id,
            @Valid
            @RequestBody WorkspaceRequestDto requestDto,
            HttpServletRequest request){
        WorkspaceResponseDto updateWorkspace = workspaceService.updateWorkspace(workspace_id, requestDto.getTitle(), requestDto.getContent());

        return new ResponseEntity<>(updateWorkspace, HttpStatus.OK);
    }
    @DeleteMapping("{workspace_id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long workspace_id,
            @Valid
            HttpServletRequest request){

        workspaceService.delete(workspace_id);

        return new ResponseEntity<>(HttpStatus.OK);

    }


}
