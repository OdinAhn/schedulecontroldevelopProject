package com.schedulecontroldevelopproject.schedule.service;

import com.schedulecontroldevelopproject.schedule.dto.*;
import com.schedulecontroldevelopproject.schedule.entity.Schedule;
import com.schedulecontroldevelopproject.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        // scheduleRepository에 request를 save 할꺼야
        Schedule schedule = new Schedule(request.getName(), request.getTitle(),request.getContent());
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new CreateScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getName(),
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
                schedule.getTitle(),
                schedule.getContent()
        );

    }
    @Transactional
    public UpdateScheduleResponse updateSchedule(Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("없는 일정 입니다.")
        );
        schedule.update(
                request.getName(),
                request.getTitle(),
                request.getContent());
        return new UpdateScheduleResponse(schedule.getId());
    }

    public void deleteSchedule(Long scheduleId) {

    }
}
