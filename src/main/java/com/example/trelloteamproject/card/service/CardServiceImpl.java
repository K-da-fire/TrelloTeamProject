package com.example.trelloteamproject.card.service;

import com.example.trelloteamproject.awss3.entity.AttachFile;
import com.example.trelloteamproject.awss3.service.AttachFileService;
import com.example.trelloteamproject.card.dto.CardResponseDto;
import com.example.trelloteamproject.card.entity.Card;
import com.example.trelloteamproject.card.repository.CardRepository;
import com.example.trelloteamproject.common.Role;
import com.example.trelloteamproject.exception.ErrorCode;
import com.example.trelloteamproject.exception.NoAuthorizedException;
import com.example.trelloteamproject.exception.NotFoundException;
import com.example.trelloteamproject.invitation.entity.Invitation;
import com.example.trelloteamproject.invitation.service.InvitationService;
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
import software.amazon.ion.Timestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static com.example.trelloteamproject.exception.ErrorCode.NO_AUTHOR_READ_ONLY;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final UserService userService;
    private final ListsService listsService;
    private final AttachFileService attachFileService;
    private final InvitationService invitationService;

    @Transactional
    @Override
    public CardResponseDto create(String email, Long workspaceId, Long listId, String title, String explanation, MultipartFile image, LocalDateTime deadline) {
        User user = userService.findUserByEmailOrElseThrow(email);
        checkAuth(user, workspaceId);
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
    public String delete(Long workspaceId, String email, Long cardId) {
        User user = userService.findUserByEmailOrElseThrow(email);
        checkAuth(user, workspaceId);
        Card card = checkManager(email, cardId);
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
    public CardResponseDto update(Long workspaceId, String email, Long cardId, String title, String explanation, MultipartFile file, LocalDateTime deadline) {
        User user = userService.findUserByEmailOrElseThrow(email);
        checkAuth(user, workspaceId);
        Card card = checkManager(email, cardId);
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
        Date startTime = new Date();
        log.info(String.valueOf(Timestamp.now()));
        List<Card> cards = cardRepository.findByTitleContains(title, pageable);
        Date endTime = new Date();
        log.info(String.valueOf(Timestamp.now()));
        log.info(String.valueOf((endTime.getTime() - startTime.getTime())/1000));
        return cards.stream().map(Card::toDto).toList();
    }

    private Card checkManager(String email, Long cardId){
        Card card = findByIdOrElseThrow(cardId);
        card.checkManager(email);
        return card;
    }

    private void checkAuth(User user, Long workspaceId){
        Invitation invitation = invitationService.findInvocationByUserAndWorkspaceIdOrElseThrow(user.getId(), workspaceId);
        if(invitation.getRole().equals(Role.READ_ONLY)){
            throw new NoAuthorizedException(NO_AUTHOR_READ_ONLY);
        }
    }
}
