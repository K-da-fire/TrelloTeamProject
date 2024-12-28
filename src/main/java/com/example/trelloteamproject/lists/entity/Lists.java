package com.example.trelloteamproject.lists.entity;

import com.example.trelloteamproject.board.dto.BoardResponseDto;
import com.example.trelloteamproject.board.entity.Board;
import com.example.trelloteamproject.card.entity.Card;
import com.example.trelloteamproject.common.BaseEntity;
import com.example.trelloteamproject.lists.dto.ListsResponseDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lists")
public class Lists extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long orders;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
    @OneToMany(mappedBy = "list", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @Fetch(FetchMode.SUBSELECT)
    private List<Card> cards= new ArrayList<>();;

    public Lists(String content, Long order) {
        this.content = content;
        this.orders = order;
    }

    public Lists(String content, Long orders, Board board) {
        this.content = content;
        this.orders = orders;
        this.board = board;
    }

    public void updateLists(String content, Long orders) {
        this.content = content;
        this.orders = orders;
    }
    public static ListsResponseDto toDto(Lists lists) {
        return new ListsResponseDto(
                lists.getId(),
                lists.getContent(),
                lists.getOrders()
        );
    }
}