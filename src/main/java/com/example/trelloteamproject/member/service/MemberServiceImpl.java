package com.example.trelloteamproject.member.service;

import com.example.trelloteamproject.exception.InvalidInputException;
import com.example.trelloteamproject.exception.NotFoundException;
import com.example.trelloteamproject.member.entity.Member;
import com.example.trelloteamproject.member.repository.MemberRepository;
import com.example.trelloteamproject.util.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.trelloteamproject.exception.ErrorCode.NOT_FOUND_MEMBER;
import static com.example.trelloteamproject.exception.ErrorCode.WRONG_PASSWORD;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void deleteMember(Long id, String password) {
        Member member = findMemberByIdOrElseThrow(id);
        if(!passwordEncoder.matches(password, member.getPassword())) {
            throw new InvalidInputException(WRONG_PASSWORD);
        }
        member.delete();
        memberRepository.save(member);
    }

    private Member findMemberByIdOrElseThrow(Long id){
        return memberRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MEMBER));
    }
}
