package com.example.trelloteamproject.invitation.controller;

import com.example.trelloteamproject.common.Role;
import com.example.trelloteamproject.invitation.dto.InvitationRequestDto;
import com.example.trelloteamproject.invitation.dto.InvitationResponseDto;
import com.example.trelloteamproject.invitation.service.InvitationService;
import com.example.trelloteamproject.list.entity.List;
import com.example.trelloteamproject.workspace.dto.CreateWorkspaceRequestDto;
import com.example.trelloteamproject.workspace.dto.CreateWorkspaceResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invitation")
@RequiredArgsConstructor
public class InvitationController {
    private final InvitationService invitationService;

    @PostMapping()
    public ResponseEntity<InvitationResponseDto> save(
            @Valid
            @RequestBody InvitationRequestDto requestDto,
            HttpServletRequest httpServlet) {
//        HttpSession session = httpServlet.getSession(false);

//        Long userId = (Long) session.getAttribute("userId");

//        CreateWorkspaceResponseDto savedWorkspace = workspaceService.save(userId,requestDto);

        InvitationResponseDto savedInvitation = invitationService.save(requestDto.getEmail(), requestDto.getWorkspaceId(), Role.valueOf(requestDto.getRole()));

        return new ResponseEntity<>(savedInvitation, HttpStatus.CREATED);
    }

}
