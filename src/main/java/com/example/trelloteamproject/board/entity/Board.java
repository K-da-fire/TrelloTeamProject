package com.example.trelloteamproject.board.entity;

import com.example.trelloteamproject.common.BaseEntity;
import com.example.trelloteamproject.lists.entity.Lists;
import com.example.trelloteamproject.workspace.entity.Workspace;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(nullable = false)
    private String  content;

    @ManyToOne
    @JoinColumn(name = "workplace_id")
    private Workspace workspace;

}
