package com.example.trelloteamproject.workspace.service;


import com.example.trelloteamproject.workspace.dto.CreateWorkspaceRequestDto;
import com.example.trelloteamproject.workspace.dto.CreateWorkspaceResponseDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public interface WorkspaceService {
    CreateWorkspaceResponseDto save(CreateWorkspaceRequestDto requestDto);
}
