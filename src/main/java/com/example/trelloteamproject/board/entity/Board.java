package com.example.trelloteamproject.board.entity;

import com.example.trelloteamproject.awss3.entity.AttachFile;
import com.example.trelloteamproject.board.dto.BoardResponseDto;
import com.example.trelloteamproject.common.BaseEntity;

import com.example.trelloteamproject.workspace.entity.Workspace;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @JoinColumn(name = "workplace_id")
    private Workspace workspace;

    public Board(String title, AttachFile background) {
        this.title = title;
        this.background = background;
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
