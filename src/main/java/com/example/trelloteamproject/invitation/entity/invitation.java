package com.example.trelloteamproject.invitation.entity;


import com.example.trelloteamproject.common.BaseEntity;
import com.example.trelloteamproject.common.Role;
import com.example.trelloteamproject.member.entity.User;
import com.example.trelloteamproject.workspace.entity.Workspace;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "invitation")
public class invitation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "members_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id")
    private Workspace workSpace;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;

    public invitation(User user, Workspace workSpace, Role role) {
        this.user = user;
        this.workSpace = workSpace;
        this.role = role;
    }
}
