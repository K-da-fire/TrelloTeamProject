package com.example.trelloteamproject.user.service;

import com.example.trelloteamproject.common.Auth;
import com.example.trelloteamproject.exception.DuplicatedException;
import com.example.trelloteamproject.exception.ExceptionResponseDto;
import com.example.trelloteamproject.exception.InvalidInputException;
import com.example.trelloteamproject.exception.NotFoundException;
import com.example.trelloteamproject.user.dto.UserResponseDto;
import com.example.trelloteamproject.user.entity.User;
import com.example.trelloteamproject.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static com.example.trelloteamproject.exception.ErrorCode.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

@Sql(scripts = "classpath:/db/init_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("회원 가입 성공")
    void signUp() {
        // Given
        String email = "test1@test.com";
        String password = "test";
        String name = "name";
        Auth auth = Auth.ADMIN;
        // When
        UserResponseDto userResponseDto = userService.signUp(email, password, name, auth);
        // Then
        assertNotNull(userResponseDto);
        assertEquals(userResponseDto.getEmail(), email);
        /**
         * password 가 잘 encod 되었는지
         */
    }

    @Test
    @DisplayName("회원 가입 실패 : 중복된 아이디")
    void signUpFailExistsEmail() {
        // Given
        String email = "test@test.com";
        Auth auth = Auth.ADMIN;
        // When
        // Then
        DuplicatedException throwable = assertThrows(DuplicatedException.class,
                () -> userService.signUp(email, "password", "name", auth));
        assertThat(throwable.getErrorCode()).isEqualTo(EMAIL_EXIST);
    }

    @Test
    @DisplayName("회원 조회 성공")
    void findUserByIdOrElseThrow() {
        // Given
        User user = new User("email", "password", "name", Auth.ADMIN);
        user = userRepository.save(user);
        // When
        User findUser = userService.findUserByIdOrElseThrow(user.getId());
        // Then
        assertEquals(user.getName(), findUser.getName());
    }

    @Test
    @DisplayName("회원 조회 실패")
    void findUserByIdOrElseThrowUserNotFound() {
        // Given
        // When
        // Then
        assertThatThrownBy(() ->userService.findUserByIdOrElseThrow(100000000000000000L))
                .isInstanceOf(NotFoundException.class);
    }
}
