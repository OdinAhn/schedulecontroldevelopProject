package com.schedulecontroldevelopproject.user.service;

import com.schedulecontroldevelopproject.common.exception.LoginFailedException;
import com.schedulecontroldevelopproject.user.dto.LoginRequest;
import com.schedulecontroldevelopproject.user.dto.LoginResponse;
import com.schedulecontroldevelopproject.user.entity.User;
import com.schedulecontroldevelopproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                LoginFailedException::new
        );

        if (!user.getPassword().equals(request.getPassword())) {
            throw new LoginFailedException();
        }
        return new LoginResponse(user.getId(), user.getUsername());
    }
}