package com.example.trelloteamproject.board.repository;

import com.example.trelloteamproject.board.entity.Board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    // TODO 2 : 해당 보드의 리스트(Lists)를 리스트(java.util.List)로 가져옵니다.

    List<Board> findBoardById(Long boardId);



    List<Board> findAllByWorkspaceId(Long workspaceId);


    @Query(value = "SELECT b " +
            "FROM Board b " +
            "LEFT JOIN FETCH b.lists l " +
            "LEFT JOIN FETCH l.cards c " +
            "WHERE b.id = :boardId")
    List<Board> findBoardByListsAndCard(Long boardId);
}
