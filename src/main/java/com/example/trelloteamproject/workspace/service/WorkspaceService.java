package com.example.trelloteamproject.workspace.service;


import com.example.trelloteamproject.member.entity.User;
import com.example.trelloteamproject.workspace.dto.CreateWorkspaceRequestDto;
import com.example.trelloteamproject.workspace.dto.CreateWorkspaceResponseDto;
import com.example.trelloteamproject.workspace.entity.Workspace;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public interface WorkspaceService {
    CreateWorkspaceResponseDto save(String title,String content);

}
