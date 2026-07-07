package com.schedulecontroldevelopproject.schedule.dto;

import com.schedulecontroldevelopproject.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter

public class CreateScheduleResponse {
    // 이미 DB에서 만들어져 나왔으므로 id를 선언 할 수 있습니다
    private final Long id;
    private final String name;
    private final String title;
    private final String content;

    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CreateScheduleResponse(Long id, String name, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}

