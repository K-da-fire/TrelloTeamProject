package com.example.trelloteamproject.login.service;


import com.example.trelloteamproject.common.Auth;
import com.example.trelloteamproject.exception.DuplicatedException;
import com.example.trelloteamproject.exception.InvalidInputException;
import com.example.trelloteamproject.exception.NotFoundException;
import com.example.trelloteamproject.login.dto.MemberResponseDto;
import com.example.trelloteamproject.login.jwt.JwtTokenProvider;
import com.example.trelloteamproject.user.entity.User;
import com.example.trelloteamproject.user.repository.UserRepository;
import com.example.trelloteamproject.util.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.trelloteamproject.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public MemberResponseDto signUp(String email, String password, String name, Auth auth) {
        if(userRepository.existsByEmail(email)){
            throw new DuplicatedException(EMAIL_EXIST);
        }

        //String encodePassword = passwordEncoder.encoder(password);

        User user = userRepository.save(new User(
                email,
                password,
                name,
                auth
        ));


        return MemberResponseDto.toDto(user, null);
    }

    @Override
    public MemberResponseDto login(String email, String password) {
        // 이메일로 사용자 검색
        System.out.println("Logging in user: " + email);  // 로그 찍기

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_MEMBER));

        // 비밀번호 확인
        if (!passwordEncoder.matches(password, user.getPassword())) {
            System.out.println("Password mismatch for user: " + email);  // 로그 찍기

            throw new InvalidInputException(WRONG_PASSWORD);
        }

        // JWT 토큰 생성
        String token = jwtTokenProvider.createToken(user.getEmail(), user.getAuth(), 60*60*60L);
        System.out.println("Generated token: " + token);  // 로그 찍기

        // 로그인 성공 시 응답 DTO 생성 (token 포함)
        return MemberResponseDto.toDto(user, token);
    }


    // JWT 토큰을 이용한 회원 탈퇴 처리
    @Override
    public void deleteAccount(String token, String password) {
        // JWT 토큰 검증 및 사용자 정보 추출
        String email = jwtTokenProvider.getUsername(token);

        // 유저 아이디로 회원 정보 조회
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_MEMBER));

        // 탈퇴한 유저인지 확인
        if (user.isDeleted()) {
            throw new InvalidInputException(DELETED_USER);
        }

        // 비밀번호 확인
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidInputException(WRONG_PASSWORD);
        }

        // 탈퇴 처리
        user.setDeleted(true); // deleted 플래그 설정
        userRepository.save(user);
    }
}
