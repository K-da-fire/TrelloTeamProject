package com.example.trelloteamproject.workspace.entity;

import com.example.trelloteamproject.board.entity.Board;
import com.example.trelloteamproject.common.BaseEntity;
import com.example.trelloteamproject.workspace.dto.WorkspaceResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "workspace")
public class Workspace extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String  content;



    public Workspace(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static WorkspaceResponseDto toDto(Workspace workspace) {
        return new WorkspaceResponseDto(
                workspace.getId(),
                workspace.getTitle(),
                workspace.getContent()
        );
    }

    public void updateWorkspace(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
