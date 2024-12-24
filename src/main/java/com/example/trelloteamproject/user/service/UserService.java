package com.example.trelloteamproject.user.service;

import com.example.trelloteamproject.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void deleteMember(Long memberId, String password);


    User findMemberByIdOrElseThrow(Long id);
}
