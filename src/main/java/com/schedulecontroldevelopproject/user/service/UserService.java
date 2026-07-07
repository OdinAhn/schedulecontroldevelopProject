package com.schedulecontroldevelopproject.user.service;

import com.schedulecontroldevelopproject.user.dto.*;
import com.schedulecontroldevelopproject.user.entity.User;
import com.schedulecontroldevelopproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public CreateUserResponse save(CreateUserRequest request) {
        User user = new User(
                request.getUsername(),
                request.getEmail(),
                request.getPassword()
        );
        User savedUser = userRepository.save(user);

        return new CreateUserResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getCreatedAt(),
                savedUser.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<GetUsersResponse> getAll() {
        List<User> users = userRepository.findAll();

        List<GetUsersResponse> dtos = new ArrayList<>();
        for (User user : users) {
            dtos.add(new GetUsersResponse(user));
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public GetUserResponse getOne(Long userId) {
        User user = findUserById(userId);
        return new GetUserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    @Transactional
    public UpdateUserResponse updateUser(Long userId, UpdateUserRequest request) {
        User user = findUserById(userId);
        user.update(request.getUsername(), request.getEmail());
        return new UpdateUserResponse(user.getId());
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = findUserById(userId);
        userRepository.delete(user);
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("없는 유저 입니다.")
        );
    }

}
