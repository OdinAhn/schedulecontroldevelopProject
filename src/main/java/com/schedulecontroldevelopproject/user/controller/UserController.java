package com.schedulecontroldevelopproject.user.controller;
import com.schedulecontroldevelopproject.user.dto.*;
import com.schedulecontroldevelopproject.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<CreateUserResponse> createUser(
            // @Valid 검증 실행
            @Valid @RequestBody CreateUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(request));
    }

    @GetMapping("/users")
    public ResponseEntity<List<GetUsersResponse>> getAllUsers() {
        List<GetUsersResponse> result = userService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<GetUserResponse> getUser(@PathVariable Long userId) {
        GetUserResponse result = userService.getOne(userId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<UpdateUserResponse> updateUser(
            @PathVariable Long userId,
            @RequestBody UpdateUserRequest request) {
        UpdateUserResponse result = userService.updateUser(userId, request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
