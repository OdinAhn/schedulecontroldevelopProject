package com.schedulecontroldevelopproject.comment.service;

import com.schedulecontroldevelopproject.comment.dto.*;
import com.schedulecontroldevelopproject.comment.entity.Comment;
import com.schedulecontroldevelopproject.comment.repository.CommentRepository;
import com.schedulecontroldevelopproject.common.exception.ScheduleNotFoundException;
import com.schedulecontroldevelopproject.common.exception.UserNotFoundException;
import com.schedulecontroldevelopproject.schedule.entity.Schedule;
import com.schedulecontroldevelopproject.schedule.repository.ScheduleRepository;
import com.schedulecontroldevelopproject.user.entity.User;
import com.schedulecontroldevelopproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    // scheduleRepository, userRepository 2개 다 가져온다
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public CreateCommentResponse save(CreateCommentRequest request) {
        Schedule schedule = scheduleRepository.findById(request.getScheduleId())
                .orElseThrow(ScheduleNotFoundException::new);

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(UserNotFoundException::new);

        Comment comment = new Comment(schedule, user, request.getContent());
        Comment savedComment = commentRepository.save(comment);

        return new CreateCommentResponse(
                savedComment.getId(),
                savedComment.getUser().getUsername(),
                savedComment.getContent(),
                savedComment.getCreatedAt(),
                savedComment.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<GetCommentsResponse> getAll() {
        List<Comment> comments = commentRepository.findAll();

        List<GetCommentsResponse> dtos = new ArrayList<>();
        for (Comment comment : comments) {
            dtos.add(new GetCommentsResponse(comment));
        }
        return dtos;
    }
}