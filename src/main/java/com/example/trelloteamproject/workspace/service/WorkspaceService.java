package com.example.trelloteamproject.workspace.service;



import com.example.trelloteamproject.user.entity.User;
import com.example.trelloteamproject.workspace.dto.CreateWorkspaceResponseDto;
import java.util.List;
import com.example.trelloteamproject.workspace.dto.WorkspaceResponseDto;
import com.example.trelloteamproject.workspace.entity.Workspace;
import org.springframework.stereotype.Service;

@Service
public interface WorkspaceService {
    CreateWorkspaceResponseDto save(Long userId, String title,String content);

    List<WorkspaceResponseDto> findAllWorkspaces();
    List<WorkspaceResponseDto> findUserAndWorkspaces(Long userId);
    WorkspaceResponseDto updateWorkspace(Long userId,Long workspace_id,String title,String content);

    Workspace findWorkspaceByIdOrElseThrow(Long id);

    void delete(Long userId,Long workspaceId);

}
