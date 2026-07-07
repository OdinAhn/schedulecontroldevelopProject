package com.schedulecontroldevelopproject.schedule.dto;

import com.schedulecontroldevelopproject.schedule.entity.Schedule;
import lombok.Getter;

@Getter
public class GetSchedulesResponse {
    private final Long id;
    private final String title;

    public GetSchedulesResponse(Schedule schedule) { // 통째로 받아오자
        this.id = schedule.getId();
        this.title = schedule.getTitle();
    }
}
