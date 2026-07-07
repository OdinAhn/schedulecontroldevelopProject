package com.schedulecontroldevelopproject.schedule.service;

import com.schedulecontroldevelopproject.common.exception.ScheduleNotFoundException;
import com.schedulecontroldevelopproject.common.exception.UserNotFoundException;
import com.schedulecontroldevelopproject.schedule.dto.*;
import com.schedulecontroldevelopproject.schedule.entity.Schedule;
import com.schedulecontroldevelopproject.schedule.repository.ScheduleRepository;
import com.schedulecontroldevelopproject.user.entity.User;
import com.schedulecontroldevelopproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        User user = findUserById(request.getUserId());

        Schedule schedule = new Schedule(user, request.getTitle(), request.getContent());
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new CreateScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getUser().getUsername(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<GetSchedulesResponse> getAll(String name) {
        List<Schedule> schedules;

        if (name != null && !name.isBlank()) {
            schedules = scheduleRepository.findByUser_UsernameContaining(name);
        } else {
            schedules = scheduleRepository.findAll();
        }

        List<GetSchedulesResponse> dtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            dtos.add(new GetSchedulesResponse(schedule));
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public GetScheduleResponse getOne(Long scheduleId) {
        Schedule schedule = findScheduleById(scheduleId);
        return new GetScheduleResponse(
                schedule.getId(),
                schedule.getUser().getUsername(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    @Transactional
    public UpdateScheduleResponse updateSchedule(Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = findScheduleById(scheduleId);
        schedule.update(request.getTitle(), request.getContent());
        return new UpdateScheduleResponse(schedule.getId());
    }

    @Transactional
    public void deleteSchedule(Long scheduleId) {
        Schedule schedule = findScheduleById(scheduleId);
        scheduleRepository.delete(schedule);
    }

    private Schedule findScheduleById(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).orElseThrow(
                ScheduleNotFoundException::new
        );
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                UserNotFoundException::new
        );
    }

    @Transactional(readOnly = true)
    public Page<GetSchedulesPageResponse> getSchedulesPage(Pageable pageable) {
        return scheduleRepository.findSchedulesWithCommentCount(pageable);
    }

}