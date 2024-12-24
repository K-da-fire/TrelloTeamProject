package com.example.trelloteamproject.login.service;

import com.example.trelloteamproject.common.Auth;
import com.example.trelloteamproject.exception.DuplicatedException;
import com.example.trelloteamproject.login.dto.MemberResponseDto;
import com.example.trelloteamproject.member.entity.User;
import com.example.trelloteamproject.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.trelloteamproject.exception.ErrorCode.EMAIL_EXIST;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;

    @Override
    public MemberResponseDto signUp(String email, String password, String name, Auth auth) {
        if(userRepository.existsByEmail(email)){
            throw new DuplicatedException(EMAIL_EXIST);
        }
        User user = userRepository.save( new User(
                email,
                password,
                name,
                auth
        ));
        return MemberResponseDto.toDto(user);
    }
}
