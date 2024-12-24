package com.example.trelloteamproject.card.entity;

import com.example.trelloteamproject.card.dto.CardRequestDto;
import com.example.trelloteamproject.card.dto.CardResponseDto;
import com.example.trelloteamproject.common.BaseEntity;
import com.example.trelloteamproject.list.entity.List;
import com.example.trelloteamproject.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cards")
public class Card  extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "list_id")
    private List list;

    private String title;

    private String explanation;

    private String route;

    private LocalDateTime deadline;

    public CardResponseDto toDto(){
        return new CardResponseDto(
                title,
                explanation,
                user.getName(),
                deadline,
                getCreatedAt(),
                getUpdatedAt()
        );
    }

    public void updateCard(String titel, String explanation, String route, LocalDateTime deadline) {
        this.title = titel;
        this.explanation = explanation;
        this.route = route;
        this.deadline = deadline;
    }
}