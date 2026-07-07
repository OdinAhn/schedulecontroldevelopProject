package com.schedulecontroldevelopproject.user.controller;

import com.schedulecontroldevelopproject.user.constant.SessionConst;
import com.schedulecontroldevelopproject.user.dto.LoginRequest;
import com.schedulecontroldevelopproject.user.dto.LoginResponse;
import com.schedulecontroldevelopproject.user.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request,
            HttpServletRequest httpServletRequest) {

        LoginResponse response = loginService.login(request);

        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute(SessionConst.LOGIN_USER, response.getId());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}