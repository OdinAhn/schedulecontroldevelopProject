package com.schedulecontroldevelopproject.schedule.dto;

import lombok.Getter;

@Getter
public class UpdateScheduleResponse {

    private final Long id;

    public UpdateScheduleResponse(Long id) {
        this.id = id;
    }
}