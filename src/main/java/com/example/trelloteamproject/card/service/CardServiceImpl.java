package com.example.trelloteamproject.card.service;

import com.example.trelloteamproject.card.dto.CardResponseDto;
import com.example.trelloteamproject.card.entity.Card;
import com.example.trelloteamproject.card.repository.CardRepository;
import com.example.trelloteamproject.login.dto.MemberResponseDto;
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

    // TODO : List와 Board가 추가되었을 때 boardName 혹은 boardId로 전체 보드 조회 수정
    @Override
    public List<CardResponseDto> searchCards(String boardName, String title, String explanation, String userName, LocalDateTime deadline) {
        List<Card> cards = cardRepository.searchCards(boardName, title, explanation, userName, deadline);
        return cards.stream().map(Card::toDto).toList();
    }
}
