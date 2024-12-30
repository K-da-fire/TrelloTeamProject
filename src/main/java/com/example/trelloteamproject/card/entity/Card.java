package com.example.trelloteamproject.card.entity;

import com.example.trelloteamproject.awss3.entity.AttachFile;
import com.example.trelloteamproject.card.dto.CardResponseDto;
import com.example.trelloteamproject.common.BaseEntity;
import com.example.trelloteamproject.exception.ErrorCode;
import com.example.trelloteamproject.exception.NoAuthorizedException;
import com.example.trelloteamproject.lists.entity.Lists;
import com.example.trelloteamproject.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cards", indexes = {
        @Index(name = "title_index", columnList = "title")
})
public class Card  extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lists_id")
    private Lists list;

    private String title;

    private String explanation;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attach_file_id")
    private AttachFile attachFile = new AttachFile("", "");

    private LocalDateTime deadline;

    public Card(User user, Lists list, String title, String explanation, AttachFile attachFile, LocalDateTime deadline) {
        this.user = user;
        this.list = list;
        this.title = title;
        this.explanation = explanation;
        this.attachFile = attachFile;
        this.deadline = deadline;
    }

    public CardResponseDto toDto(){
        return new CardResponseDto(
                title,
                explanation,
                this.user.getName(),
                (attachFile != null)? attachFile.getFilePath() : "",
                deadline,
                getCreatedAt(),
                getUpdatedAt()
        );
    }

    public void updateCard(String title, String explanation, AttachFile attachFile, LocalDateTime deadline) {
        this.title = title;
        this.explanation = explanation;
        this.attachFile = attachFile;
        this.deadline = deadline;
    }

    public void checkAuth(String email){
        if(!this.user.getEmail().equals(email)){
            throw new NoAuthorizedException(ErrorCode.NO_AUTHOR_CHANGE);
        }
    }
}
