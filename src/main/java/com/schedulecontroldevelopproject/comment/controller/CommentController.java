package com.schedulecontroldevelopproject.comment.controller;

import com.schedulecontroldevelopproject.comment.dto.*;
import com.schedulecontroldevelopproject.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<CreateCommentResponse> createComment(
            @Valid @RequestBody CreateCommentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(request));
    }

    @GetMapping("/comments")
    public ResponseEntity<List<GetCommentsResponse>> getAllComments() {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getAll());
    }
}