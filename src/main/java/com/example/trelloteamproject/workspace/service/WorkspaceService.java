package com.example.trelloteamproject.workspace.service;



import com.example.trelloteamproject.lists.dto.ListsResponseDto;
import com.example.trelloteamproject.user.entity.User;
import com.example.trelloteamproject.workspace.dto.CreateWorkspaceResponseDto;
import java.util.List;
import com.example.trelloteamproject.workspace.dto.WorkspaceResponseDto;
import com.example.trelloteamproject.workspace.entity.Workspace;
import org.springframework.stereotype.Service;

@Service
public interface WorkspaceService {
    CreateWorkspaceResponseDto save(String email, String title,String content);

    List<WorkspaceResponseDto> findAllWorkspaces();
    List<WorkspaceResponseDto> findUserAndWorkspaces(String email);
    WorkspaceResponseDto updateWorkspace(String email,Long workspace_id,String title,String content);

    Workspace findWorkspaceByIdOrElseThrow(Long id);


    void delete(String email,Long workspaceId);

}
