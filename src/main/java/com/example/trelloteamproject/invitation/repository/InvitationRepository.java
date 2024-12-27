package com.example.trelloteamproject.invitation.repository;

import com.example.trelloteamproject.invitation.entity.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    Optional<Invitation> findByUserIdAndWorkspaceId(Long userId, Long workspaceId);

}
