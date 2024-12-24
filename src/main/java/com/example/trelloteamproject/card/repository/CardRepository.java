package com.example.trelloteamproject.card.repository;

import com.example.trelloteamproject.card.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long>, CardQueryDsl {
    List<Card> findByListId(Long listId);
}