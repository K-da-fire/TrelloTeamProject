package com.example.trelloteamproject.card.repository;

import com.example.trelloteamproject.card.entity.Card;

import com.example.trelloteamproject.card.entity.QCard;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;

// TODO : List와 Board가 추가되었을 때 boardName 혹은 boardId로 전체 보드 조회 수정
// boardId로 수정한다면 findByName 등의 함수로 boardId를 찾은 후 검색 쿼리 개선
@RequiredArgsConstructor
public class CardQueryDslImpl implements CardQueryDsl {

    private final JPAQueryFactory jpaQueryFactor;

    @Override
    public List<Card> searchCards(String boardName, String title, String content, String userName, LocalDateTime deadline) {
        QCard card = QCard.card;
        
        JPAQuery<Card> query = jpaQueryFactor.selectFrom(card);

//        if(boardName != null) {
//            query = query.where(card.list.board.name.eq(boardName));
//        }
        if(title != null){
            query = query.where(card.title.contains(title));
        }
        if(content != null){
            query = query.where(card.explanation.contains(content));
        }
        if(userName != null){
            query = query.where(card.user.name.eq(userName));
        }
        // 오늘부터 주어진 날짜까지 deadline인 모든 엔티티
        if(deadline != null){
            query = query.where(card.deadline.between(LocalDateTime.now(), deadline));
        }

        return query.fetch();
    }
}
