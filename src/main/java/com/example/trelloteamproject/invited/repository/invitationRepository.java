package com.example.trelloteamproject.invited.repository;

import com.example.trelloteamproject.invited.entity.invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface invitationRepository extends JpaRepository<invitation, Long> {
}
