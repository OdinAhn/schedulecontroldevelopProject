package com.schedulecontroldevelopproject.comment.dto;

import com.schedulecontroldevelopproject.comment.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetCommentsResponse {

    private final Long id;
    private final String username;

    private final String content;
    private final LocalDateTime createdAt;

    public GetCommentsResponse(Comment comment) {
        this.id = comment.getId();
        this.username = comment.getUser().getUsername();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
    }
}