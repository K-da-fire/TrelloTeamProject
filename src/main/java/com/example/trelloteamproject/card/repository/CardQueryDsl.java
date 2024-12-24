package com.example.trelloteamproject.card.repository;

import java.time.LocalDateTime;
import java.util.List;
import com.example.trelloteamproject.card.entity.Card;

public interface CardQueryDsl {
    List<Card> searchCards(String boardName, String title, String content, String userName, LocalDateTime deadline);
}
