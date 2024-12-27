package com.example.trelloteamproject.user.service;

import com.example.trelloteamproject.common.Auth;
import com.example.trelloteamproject.exception.DuplicatedException;
import com.example.trelloteamproject.exception.InvalidInputException;
import com.example.trelloteamproject.exception.NotFoundException;
import com.example.trelloteamproject.user.dto.UserResponseDto;
import com.example.trelloteamproject.user.entity.User;
import com.example.trelloteamproject.user.repository.UserRepository;
import com.example.trelloteamproject.util.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.trelloteamproject.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto signUp(String email, String password, String name, Auth auth) {
        if(userRepository.existsByEmail(email)){
            throw new DuplicatedException(EMAIL_EXIST);
        }
        User user = userRepository.save( new User(
                email,
                password,
                name,
                auth
        ));
        return UserResponseDto.toDto(user);
    }

    @Override
    public void deleteUser(Long userId, String password) {
        User user = findUserByIdOrElseThrow(userId);
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidInputException(WRONG_PASSWORD);
        }
        user.delete();
        userRepository.save(user);
    }

    @Override
    public User findUserByIdOrElseThrow(Long id){
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_USER));
    }
}
