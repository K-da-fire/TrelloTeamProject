package com.example.trelloteamproject.invited.entity;


import com.example.trelloteamproject.common.Auth;
import com.example.trelloteamproject.common.BaseEntity;
import com.example.trelloteamproject.common.Role;
import com.example.trelloteamproject.member.entity.Member;
import com.example.trelloteamproject.workspace.entity.Workspace;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "invited")
public class Invited extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "members_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id")
    private Workspace workSpace;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Auth auth;

    public Invited(Member member, Workspace workSpace, Auth auth) {
        this.member = member;
        this.workSpace = workSpace;
        this.auth = auth;
    }
}
