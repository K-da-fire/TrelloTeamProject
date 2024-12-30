package com.example.trelloteamproject.lists.repository;

import com.example.trelloteamproject.lists.entity.Lists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ListsRepository extends JpaRepository<Lists, Long> {



    List<Lists> findAllByBoardIdIn(List<Long> boardId);

    List<Lists> findAllByBoardId(Long boardId);

    List<Lists> findListsByCardsId(Long cardId);


    List<Lists> findAllByOrderByOrdersAsc();
}
