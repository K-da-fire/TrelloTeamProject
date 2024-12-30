package com.example.trelloteamproject.card.service;

import com.example.trelloteamproject.card.dto.CardResponseDto;
import com.example.trelloteamproject.card.entity.Card;
import com.example.trelloteamproject.card.repository.CardRepository;
import com.example.trelloteamproject.exception.NotFoundException;
import com.example.trelloteamproject.lists.entity.Lists;
import com.example.trelloteamproject.lists.repository.ListsRepository;
import com.example.trelloteamproject.user.entity.User;
import com.example.trelloteamproject.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@Sql(scripts = "classpath:/db/init_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
@SpringBootTest
class CardServiceImplTest {

    @Autowired
    private CardService cardService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ListsRepository listsRepository;

    @Autowired
    private CardRepository cardRepository;

    @Test
    @DisplayName("카드 생성")
    void create() {
        // Given
        User user = userRepository.findById(1L).get();
        Lists lists = listsRepository.findById(1L).get();
        String title = "title";
        String explanation = "explanation";
        MultipartFile image = null;
        LocalDateTime deadline = LocalDateTime.now();
        // When
        CardResponseDto card = cardService.create(user.getEmail(), , lists.getId(), title, explanation, image, deadline);
        // Then
        assertNotNull(card);
    }

    @Test
    void searchCards() {
    }

    @Test
    void delete() {
        // Given
        User user = userRepository.findById(1L).get();
        Card card = cardRepository.findById(1L).get();
        // When
        cardService.delete(, user.getEmail(), card.getId());
        // Then
        assertThatThrownBy(() ->cardService.findByIdOrElseThrow(card.getId()))
                .isInstanceOf(NotFoundException.class);
    }

    @Transactional
    @Test
    void update() {
        // Given
        User user = userRepository.findById(1L).get();
        Card card = cardRepository.findById(1L).get();
        String title = "update title";
        String explanation = "update explanation";
        MultipartFile image = null;
        LocalDateTime deadline = LocalDateTime.now();
        // When
        CardResponseDto cardResponseDto = cardService.update(, user.getEmail(), card.getId(), title, explanation, image, deadline);
        // Then
        assertNotNull(cardResponseDto);
    }
}
