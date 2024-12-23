package com.example.trelloteamproject.member.service;

import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    void deleteMember(Long memberId, String password);
}
