package com.example.trelloteamproject.invitation.entity;


import com.example.trelloteamproject.common.BaseEntity;
import com.example.trelloteamproject.common.Role;
import com.example.trelloteamproject.invitation.dto.InvitationResponseDto;
import com.example.trelloteamproject.user.entity.User;
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
public class Invitation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id")
    private Workspace workSpace;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;

    public Invitation(User user, Workspace workSpace, Role role) {
        this.user = user;
        this.workSpace = workSpace;
        this.role = role;
    }

    public Invitation(User user, Workspace workSpace) {
        this.user = user;
        this.workSpace = workSpace;
    }
    public static InvitationResponseDto toDto(Invitation invitation) {
        return new InvitationResponseDto(
                invitation.getUser().getEmail(),
                invitation.workSpace.getId()

        );
    }
}
