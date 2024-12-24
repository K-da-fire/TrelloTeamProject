package com.example.trelloteamproject.member.service;

import com.example.trelloteamproject.exception.InvalidInputException;
import com.example.trelloteamproject.exception.NotFoundException;
import com.example.trelloteamproject.member.entity.User;
import com.example.trelloteamproject.member.repository.UserRepository;
import com.example.trelloteamproject.util.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.trelloteamproject.exception.ErrorCode.NOT_FOUND_MEMBER;
import static com.example.trelloteamproject.exception.ErrorCode.WRONG_PASSWORD;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void deleteMember(Long id, String password) {
        User user = findMemberByIdOrElseThrow(id);
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidInputException(WRONG_PASSWORD);
        }
        user.delete();
        userRepository.save(user);
    }

    @Override
    public User findMemberByIdOrElseThrow(Long id){
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_MEMBER));
    }
}
