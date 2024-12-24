package com.example.trelloteamproject.member.service;

import com.example.trelloteamproject.member.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void deleteMember(Long memberId, String password);


    User findMemberByIdOrElseThrow(Long id);
}
