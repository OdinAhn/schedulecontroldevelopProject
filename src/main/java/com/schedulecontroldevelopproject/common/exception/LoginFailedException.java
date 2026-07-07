package com.schedulecontroldevelopproject.common.exception;

import org.springframework.http.HttpStatus;

public class LoginFailedException extends CustomException {
    public LoginFailedException() {
        super(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 일치하지 않습니다.");
    }
}