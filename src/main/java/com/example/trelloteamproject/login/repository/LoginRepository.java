package com.example.trelloteamproject.login.repository;

import com.example.trelloteamproject.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Member, Long> {
}
