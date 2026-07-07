package com.schedulecontroldevelopproject.user.dto;

import com.schedulecontroldevelopproject.user.entity.User;
import lombok.Getter;

@Getter
public class GetUsersResponse {
    private final Long id;
    private final String username;

    public GetUsersResponse(User user) { // 통째로 받아오자
        this.id = user.getId();
        this.username = user.getUsername();    }
}
