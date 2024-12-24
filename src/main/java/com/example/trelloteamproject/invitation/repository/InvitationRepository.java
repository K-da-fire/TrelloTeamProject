package com.example.trelloteamproject.invitation.repository;

import com.example.trelloteamproject.invitation.entity.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    Invitation findInvitationByRole(Invitation invitation);
}
