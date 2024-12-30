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


//    @Query("SELECT b FROM Board b " +
//            "JOIN FETCH b.lists bl " +
//            "WHERE b.id = :boardId AND EXISTS (" +
//            "SELECT l FROM Lists l " +
//            "WHERE l.board.id = b.id)")
//    Board findBoardByListsAndCard2(Long boardId);

    @Query("SELECT b FROM Board b " +
            "JOIN FETCH b.lists bl " +
            "WHERE b.id = :boardId")
    Board findBoardByListsAndCard(Long boardId);

//    @Query("SELECT b FROM Board b " +
//            "JOIN FETCH b.lists bl " +
//            "JOIN FETCH bl.cards blc " +
//            "WHERE b.id = :boardId")
//    Board findBoardByListsAndCard3(Long boardId);

    //엔티티 그래프를 이용하면 페치를 2개 이용가능




}
