package com.schedulecontroldevelopproject.common.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends CustomException {

    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다.");
    }
}