package com.example.trelloteamproject.board.entity;

import com.example.trelloteamproject.awss3.entity.AttachFile;
import com.example.trelloteamproject.board.dto.BoardResponseDto;
import com.example.trelloteamproject.card.entity.Card;
import com.example.trelloteamproject.common.BaseEntity;

import com.example.trelloteamproject.lists.entity.Lists;
import com.example.trelloteamproject.workspace.entity.Workspace;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "board")
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;

    @Setter
    @OneToOne
    private AttachFile background;

    @ManyToOne
    @JoinColumn(name = "workspace_id")
    private Workspace workspace;

//    @JsonIgnoreProperties({"board"})
    @BatchSize(size = 3)
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lists> lists= new ArrayList<>();;

//    @JsonIgnoreProperties({"board"})
//    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
//    private List<Card> cards;



    public Board(String title, AttachFile background) {
        this.title = title;
        this.background = background;
    }

    public Board(String title, AttachFile background, Workspace workspace) {
        this.title = title;
        this.background = background;
        this.workspace = workspace;
    }

    public void updateBoard(String title, AttachFile background) {
        this.title = title;
        this.background = background;
    }

    public static BoardResponseDto toDto(Board board) {
        return new BoardResponseDto(
                board.getId(),
                board.getTitle(),
                board.getBackground().getFileName()
        );
    }
}
