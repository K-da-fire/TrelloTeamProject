package com.example.trelloteamproject.login.service;

import com.example.trelloteamproject.login.dto.MemberRequestDto;
import com.example.trelloteamproject.login.dto.MemberResponseDto;
import com.example.trelloteamproject.login.repository.LoginRepository;
import com.example.trelloteamproject.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;

    @Override
    public MemberResponseDto signUp(MemberRequestDto memberRequestDto) {
        Member member = loginRepository.save( new Member (
            memberRequestDto.getEmail(),
                    memberRequestDto.getPassword(),
                    memberRequestDto.getName(),
                    memberRequestDto.getRole()
        ));
        return MemberResponseDto.toDto(member);
    }
}
