package com.schedulecontroldevelopproject.common.exception;

import org.springframework.http.HttpStatus;

public class ScheduleNotFoundException extends CustomException {

    public ScheduleNotFoundException() {
        super(HttpStatus.NOT_FOUND,"일정이 없습니다.");
    }
}
