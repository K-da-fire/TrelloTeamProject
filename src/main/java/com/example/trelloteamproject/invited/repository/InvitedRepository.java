package com.example.trelloteamproject.invited.repository;

import com.example.trelloteamproject.invited.entity.Invited;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitedRepository extends JpaRepository<Invited, Long> {
}
