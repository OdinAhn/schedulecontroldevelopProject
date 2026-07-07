package com.schedulecontroldevelopproject.user.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter

public class CreateUserResponse {
    // 이미 DB에서 만들어져 나왔으므로 id를 선언 할 수 있습니다
    private final Long id;
    private final String username;
    private final String email;

    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CreateUserResponse(Long id, String username, String email, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}

