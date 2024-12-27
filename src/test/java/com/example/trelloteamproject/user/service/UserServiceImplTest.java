package com.example.trelloteamproject.user.service;

import com.example.trelloteamproject.common.Auth;
import com.example.trelloteamproject.exception.DuplicatedException;
import com.example.trelloteamproject.exception.ExceptionResponseDto;
import com.example.trelloteamproject.exception.InvalidInputException;
import com.example.trelloteamproject.exception.NotFoundException;
import com.example.trelloteamproject.user.dto.UserResponseDto;
import com.example.trelloteamproject.user.entity.User;
import com.example.trelloteamproject.user.repository.UserRepository;
import org.hibernate.annotations.processing.SQL;
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

//@Sql(scripts = "classpath:/db/init_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
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
        String email = "test@test.com";
        String password = "test";
        String name = "name";
        Auth auth = Auth.ADMIN;
        // When
        UserResponseDto userResponseDto = userService.signUp(email, password, name, auth);
        // Then
        assertNotNull(userResponseDto);
        assertEquals(userResponseDto.getEmail(), email);
    }

    @Test
    @DisplayName("회원 가입 실패 : 중복된 아이디")
    void signUpFailExistsEmail() {
        // Given
        String email = "test@test.com";
        String password = "test";
        String name = "name";
        Auth auth = Auth.ADMIN;
        userService.signUp(email, password, name, auth);
        // When
        Throwable thrown = catchThrowable(() -> userService.signUp(email, "password", "name", auth));
        // Then
        assertThat(thrown)
                .isInstanceOf(DuplicatedException.class);
    }

    @Test
    @DisplayName("회원 삭제 성공")
    void deleteUser() {
        // Given
        String email = "test@test.com";
        String password = "test";
        String name = "name";
        Auth auth = Auth.ADMIN;
        User user = userRepository.save(new User(email, password, name, auth));
        // When
        userService.deleteUser(user.getId(), user.getPassword());
        // Then
        assertThatThrownBy(() ->userService.findUserByIdOrElseThrow(user.getId()))
                .isInstanceOf(InvalidInputException.class);
    }

    @Test
    @DisplayName("회원 삭제 비밀번호 매치 실패")
    void deleteUserPasswordMatchFail() {
        // Given
        String email = "test@test.com";
        String password = "test";
        String name = "name";
        Auth auth = Auth.ADMIN;
        User user = userRepository.save(new User(email, password, name, auth));
        // When
        // Then
        assertThatThrownBy(() ->userService.deleteUser(user.getId(), "wrong password"))
                .isInstanceOf(InvalidInputException.class);
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
