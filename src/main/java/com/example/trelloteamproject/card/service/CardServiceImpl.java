package com.example.trelloteamproject.card.service;

import com.example.trelloteamproject.card.repository.CardRepository;
import com.example.trelloteamproject.login.dto.MemberResponseDto;
import com.example.trelloteamproject.member.entity.User;
import com.example.trelloteamproject.member.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final UserService userService;
//    private final ListService listService;
//    private final BoardService boardService;
//    private final WorkspaceService workspaceService;

    @Override
    public MemberResponseDto create(Long id, String title, String explanation, String route, LocalDateTime deadline) {
        User user = userService.findMemberByIdOrElseThrow(id);
        return null;
    }
}
