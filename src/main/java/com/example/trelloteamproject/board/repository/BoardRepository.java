package com.example.trelloteamproject.board.repository;

import com.example.trelloteamproject.board.entity.Board;
import com.example.trelloteamproject.workspace.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

//    List<WorkspaceResponseDto> findAllByUserId(Long id);
}
