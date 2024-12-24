package com.example.trelloteamproject.workspace.controller;


import com.example.trelloteamproject.workspace.dto.CreateWorkspaceRequestDto;
import com.example.trelloteamproject.workspace.dto.CreateWorkspaceResponseDto;
import com.example.trelloteamproject.workspace.service.WorkspaceService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        CreateWorkspaceResponseDto savedWorkspace = workspaceService.save(requestDto);

        return new ResponseEntity<>(savedWorkspace, HttpStatus.CREATED);

    }
}
