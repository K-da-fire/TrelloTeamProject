package com.example.trelloteamproject.board.repository;

import com.example.trelloteamproject.board.entity.Board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {


    List<Board> findBoardById(Long boardId);



    List<Board> findAllByWorkspaceId(Long workspaceId);


    @Query("SELECT b FROM Board b " +
            "JOIN FETCH b.lists bl " +
            "WHERE b.id = :boardId AND EXISTS (" +
            "SELECT l FROM Lists l " +
            "WHERE l.board.id = b.id)")
    Board findBoardByListsAndCard(Long boardId);
}
