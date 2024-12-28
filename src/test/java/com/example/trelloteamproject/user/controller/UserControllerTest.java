package com.example.trelloteamproject.user.controller;

import com.example.trelloteamproject.common.Auth;
import com.example.trelloteamproject.common.LoginStatus;
import com.example.trelloteamproject.login.entity.SessionDto;
import com.example.trelloteamproject.user.dto.UserResponseDto;
import com.example.trelloteamproject.user.entity.User;
import com.example.trelloteamproject.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    private MockHttpSession session;

    @BeforeEach
    void setUp() {
        SessionDto sessionDto = new SessionDto(1L, Auth.ADMIN);
        session = new MockHttpSession();
        session.setAttribute(LoginStatus.LOGIN_USER, sessionDto);
    }

    @Test
    @DisplayName("회원 가입")
    void signUp() throws Exception {
        // Given
        // When
        // Then
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        """
                {
                  "email": "test@example.com",
                  "password": "aA123456789!",
                  "name": "test",
                  "auth": "ADMIN"
                }
                """))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원 탈퇴")
    void deleteUser() throws Exception {
        // Given
        // When
        // Then
        mockMvc.perform( delete("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                        {
                          "password": "aA123456789!"
                        }
                        """)
                        .session(session))
                .andExpect(status().isOk());
    }
}