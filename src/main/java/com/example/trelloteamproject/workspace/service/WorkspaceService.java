package com.example.trelloteamproject.workspace.service;



import com.example.trelloteamproject.workspace.dto.CreateWorkspaceResponseDto;

import org.springframework.stereotype.Service;

@Service
public interface WorkspaceService {
    CreateWorkspaceResponseDto save(String title,String content);

}
