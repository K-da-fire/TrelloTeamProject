package com.example.trelloteamproject.lists.repository;

import com.example.trelloteamproject.board.entity.Board;
import com.example.trelloteamproject.lists.entity.Lists;
import com.example.trelloteamproject.workspace.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListsRepository extends JpaRepository<Lists, Long> {

//    List<WorkspaceResponseDto> findAllByUserId(Long id);

    List<Lists> findAllByIdIn(List<Long> id);
}
