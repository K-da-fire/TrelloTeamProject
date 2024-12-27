package com.example.trelloteamproject.lists.repository;

import com.example.trelloteamproject.lists.entity.Lists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListsRepository extends JpaRepository<Lists, Long> {


    // TODO 1 : 여기서 해당 list의 cards를 조회하여 ListsCardsResponseDto에 넣어주어야 합니다. -> ListsCrdsResponseDto 완성 -> Card 리스트는 나중에 조회할 필요가 없어요.

    List<Lists> findAllByBoardIdIn(List<Long> boardId);

    List<Lists> findAllByBoardId(Long boardId);

    List<Lists> findListsByCardsId(Long cardId);


}
