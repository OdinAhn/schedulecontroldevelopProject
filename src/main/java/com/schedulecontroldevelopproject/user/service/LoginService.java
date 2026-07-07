package com.schedulecontroldevelopproject.user.service;

import com.schedulecontroldevelopproject.common.config.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder; // 암호화 추가

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                LoginFailedException::new
        );

        // equals 대신에 passwordEncoder.matches() 적용
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
        //if (!user.getPassword().equals(request.getPassword())) {
            throw new LoginFailedException();
        }
        return new LoginResponse(user.getId(), user.getUsername());
    }
}