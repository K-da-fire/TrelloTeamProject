package com.example.trelloteamproject.comment.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String content;

    public void setContent(String content) {
        this.content = content;
    }
}
