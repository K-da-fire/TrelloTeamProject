package com.example.trelloteamproject.card.service;

import com.example.trelloteamproject.awss3.entity.AttachFile;
import com.example.trelloteamproject.awss3.service.AttachFileService;
import com.example.trelloteamproject.card.dto.CardResponseDto;
import com.example.trelloteamproject.card.entity.Card;
import com.example.trelloteamproject.card.repository.CardRepository;
import com.example.trelloteamproject.exception.ErrorCode;
import com.example.trelloteamproject.exception.NotFoundException;
import com.example.trelloteamproject.lists.entity.Lists;
import com.example.trelloteamproject.lists.service.ListsService;
import com.example.trelloteamproject.user.entity.User;
import com.example.trelloteamproject.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final UserService userService;
    private final ListsService listsService;
    private final AttachFileService attachFileService;

    @Transactional
    @Override
    public CardResponseDto create(Long userId, Long listId, String title, String explanation, MultipartFile image, LocalDateTime deadline) {
        User user = userService.findUserByIdOrElseThrow(userId);
        Lists list = listsService.findListsByIdOrElseThrow(listId);
        AttachFile attachFile = null;
        if(image != null) {
            attachFile = attachFileService.uploadFile(image);
        }
        Card card = new Card(user, list, title, explanation, attachFile, deadline);
        return cardRepository.save(card).toDto();
    }

    @Override
    public CardResponseDto findById(Long id) {
        return findByIdOrElseThrow(id).toDto();
    }

    @Override
    public List<CardResponseDto> searchCards(Long boardId, String title, String explanation, String userName, LocalDateTime deadline) {
        List<Card> cards = cardRepository.searchCards(boardId, title, explanation, userName, deadline);
        return cards.stream().map(Card::toDto).toList();
    }

    @Override
    public Card findByIdOrElseThrow(Long id) {
        return cardRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_CARD));
    }

    @Transactional
    @Override
    public String delete(Long userId, Long cardId) {
        Card card = checkManager(userId, cardId);
        card.setAttachFile(null);
        cardRepository.save(card);
        if(card.getAttachFile() != null) {
            attachFileService.deleteFile(card.getAttachFile().getFileName());
        }
        cardRepository.delete(card);
        return card.getTitle();
    }

    @Transactional
    @Override
    public CardResponseDto update(Long userId, Long cardId, String title, String explanation, MultipartFile file, LocalDateTime deadline) {
        Card card = checkManager(userId, cardId);
        AttachFile fileName = card.getAttachFile();
        if(file != null){
            card.setAttachFile(null);
            cardRepository.save(card);
            attachFileService.deleteFile(fileName.getFileName());
            fileName = attachFileService.uploadFile(file);
        }
        // 각 값이 주어지지 않는다면 원래값을 유지한다.
        card.updateCard(
                title.isEmpty()? card.getTitle(): title,
                explanation.isEmpty()? card.getExplanation() : explanation,
                fileName,
                deadline == null? card.getDeadline(): deadline);
        cardRepository.save(card);

        return card.toDto();
    }

    @Override
    public List<CardResponseDto> findByListId(Long listId) {
        List<Card> cards = cardRepository.findByListId(listId);
        return cards.stream().map(Card::toDto).toList();
    }

    @Override
    public List<CardResponseDto> searchCardsByTitle(String title, Pageable pageable) {
        List<Card> cards = cardRepository.findByTitleContains(title, pageable);
        return cards.stream().map(Card::toDto).toList();
    }

    private Card checkManager(Long userId, Long cardId){
        Card card = findByIdOrElseThrow(cardId);
        card.checkAuth(userId);
        return card;
    }
}
