package com.example.trelloteamproject.card.service;

import com.example.trelloteamproject.card.dto.CardResponseDto;
import com.example.trelloteamproject.card.entity.Card;
import com.example.trelloteamproject.card.repository.CardRepository;
import com.example.trelloteamproject.exception.ErrorCode;
import com.example.trelloteamproject.exception.NoAuthorizedException;
import com.example.trelloteamproject.exception.NotFoundException;
import com.example.trelloteamproject.user.entity.User;
import com.example.trelloteamproject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final UserService userService;
//    private final ListService listService;
//    private final BoardService boardService;
//    private final WorkspaceService workspaceService;

    @Override
    public CardResponseDto create(Long id, String title, String explanation, String route, LocalDateTime deadline) {
        User user = userService.findMemberByIdOrElseThrow(id);
        return null;
    }

    @Override
    public CardResponseDto findById(Long id) {
        return findByIdOrElseThrow(id).toDto();
    }

    // TODO : List와 Board가 추가되었을 때 boardName 혹은 boardId로 전체 보드 조회 수정
    @Override
    public List<CardResponseDto> searchCards(String boardName, String title, String explanation, String userName, LocalDateTime deadline) {
        List<Card> cards = cardRepository.searchCards(boardName, title, explanation, userName, deadline);
        return cards.stream().map(Card::toDto).toList();
    }

    @Override
    public Card findByIdOrElseThrow(Long id) {
        return cardRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_CARD));
    }

    // TODO : CASCADE구현을 해야합니다.
    @Override
    public String delete(Long userId, Long cardId) {
        Card card = checkManager(userId, cardId);
        cardRepository.delete(card);
        return card.getTitle();
    }

    @Override
    public CardResponseDto update(Long userId, Long cardId, String title, String explanation, String route, LocalDateTime deadline) {
        Card card = checkManager(userId, cardId);
        // 각 값이 주어지지 않는다면 원래값을 유지한다.
        card.updateCard(
                title.isEmpty()? card.getTitle(): title,
                explanation.isEmpty()? card.getExplanation() : explanation,
                route.isEmpty()? card.getRoute() : route,
                deadline == null? card.getDeadline(): deadline);
        cardRepository.save(card);

        return card.toDto();
    }

    @Override
    public List<CardResponseDto> findByListId(Long listId) {
        List<Card> cards = cardRepository.findByListId(listId);
        return cards.stream().map(Card::toDto).toList();
    }

    private Card checkManager(Long userId, Long cardId){
        Card card = findByIdOrElseThrow(cardId);
        if(!card.getUser().getId().equals(userId)){
            throw new NoAuthorizedException(ErrorCode.NO_AUTHOR_CHANGE);
        }
        return card;
    }
}
