package com.example.trelloteamproject.login.service;

import com.example.trelloteamproject.common.Role;
import com.example.trelloteamproject.common.Auth;
import com.example.trelloteamproject.exception.DuplicatedException;
import com.example.trelloteamproject.login.dto.MemberRequestDto;
import com.example.trelloteamproject.login.dto.MemberResponseDto;
import com.example.trelloteamproject.member.entity.Member;
import com.example.trelloteamproject.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.trelloteamproject.exception.ErrorCode.EMAIL_EXIST;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final MemberRepository memberRepository;

    @Override
    public MemberResponseDto signUp(String email, String password, String name, Auth auth, Role role) {
        if(memberRepository.existsByEmail(email)){
            throw new DuplicatedException(EMAIL_EXIST);
        }
        Member member = memberRepository.save( new Member (
                email,
                password,
                name,
                auth,
                role
        ));
        return MemberResponseDto.toDto(member);
    }
}
