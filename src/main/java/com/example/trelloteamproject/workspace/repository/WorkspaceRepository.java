package com.example.trelloteamproject.workspace.repository;

import com.example.trelloteamproject.user.entity.User;
import com.example.trelloteamproject.workspace.dto.WorkspaceResponseDto;
import com.example.trelloteamproject.workspace.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {

    List<Workspace> findAllByIdIn(List<Long> id);
    Workspace findWorkspaceById(Long id);
}
