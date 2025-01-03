package com.example.trelloteamproject.card.repository;

import com.example.trelloteamproject.card.entity.Card;

import com.example.trelloteamproject.card.entity.QCard;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class CardQueryDslImpl implements CardQueryDsl {

    private final JPAQueryFactory jpaQueryFactor;
    
    @Override
    public List<Card> searchCards(Long boardId, String title, String content, String userName, LocalDateTime deadline) {
        QCard card = QCard.card;
        
        JPAQuery<Card> query = jpaQueryFactor.selectFrom(card);

        if(boardId != null) {
            query = query.where(card.list.board.id.eq(boardId));
        }
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
