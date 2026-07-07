package com.schedulecontroldevelopproject.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetSchedulesPageResponse {

    private final Long id;
    private final String username;
    private final String title;
    private final String content;
    private final Long commentCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public GetSchedulesPageResponse(Long id, String username, String title, String content, Long commentCount, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.content = content;
        this.commentCount = commentCount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}