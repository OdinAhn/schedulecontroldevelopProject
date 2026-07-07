package com.schedulecontroldevelopproject.user.dto;

import lombok.Getter;

@Getter

public class LoginResponse {

    private final Long id;
    private final String username;

    public LoginResponse(Long id, String username) {
        this.id = id;
        this.username = username;
    }

}
