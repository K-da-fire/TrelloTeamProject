package com.example.trelloteamproject.card.entity;

import com.example.trelloteamproject.card.dto.CardRequestDto;
import com.example.trelloteamproject.card.dto.CardResponseDto;
import com.example.trelloteamproject.common.BaseEntity;
import com.example.trelloteamproject.lists.entity.Lists;
import com.example.trelloteamproject.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

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
    @JoinColumn(name = "lists_id")
    private Lists list;

    private String title;

    private String explanation;

    private String fileName;

    private LocalDateTime deadline;

    public Card(User user, Lists list, String title, String explanation, String fileName, LocalDateTime deadline) {
        this.user = user;
        this.list = list;
        this.title = title;
        this.explanation = explanation;
        this.fileName = fileName;
        this.deadline = deadline;
    }

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

    public void updateCard(String title, String explanation, String fileName, LocalDateTime deadline) {
        this.title = title;
        this.explanation = explanation;
        this.fileName = fileName;
        this.deadline = deadline;
    }
}
