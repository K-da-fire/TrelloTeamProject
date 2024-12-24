package com.example.trelloteamproject.invitation.repository;

import com.example.trelloteamproject.invitation.entity.invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface invitationRepository extends JpaRepository<invitation, Long> {
}
