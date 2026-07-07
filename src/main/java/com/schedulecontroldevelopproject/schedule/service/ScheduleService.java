package com.schedulecontroldevelopproject.schedule.service;

import com.schedulecontroldevelopproject.schedule.dto.*;
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

public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {

        // name대신 userId를 쓰고 FK로 들어왔음
        User user = userRepository.findById(request.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("없는 유저 입니다.")
        );

        // scheduleRepository에 request를 save 할꺼야
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
        List<Schedule> schedules = scheduleRepository.findAll();

        List<GetSchedulesResponse> dtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            GetSchedulesResponse dto = new GetSchedulesResponse(schedule);
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public GetScheduleResponse getOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("없는 일정 입니다.")
        );
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
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("없는 일정 입니다.")
        );
        schedule.update(
                request.getTitle(),
                request.getContent());
        return new UpdateScheduleResponse(schedule.getId());
    }

    public void deleteSchedule(Long scheduleId) {

    }
}
