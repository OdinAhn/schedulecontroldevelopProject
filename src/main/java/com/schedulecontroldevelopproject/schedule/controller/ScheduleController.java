package com.schedulecontroldevelopproject.schedule.controller;

import com.schedulecontroldevelopproject.schedule.dto.*;
import com.schedulecontroldevelopproject.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> createSchedule(
            @RequestBody CreateScheduleRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(request));
    }

    // Search ALL
    @GetMapping("/schedules")
    public ResponseEntity<List<GetSchedulesResponse>> getAllSchedules(
            @RequestParam(required = false) String name) {
        List<GetSchedulesResponse> result = scheduleService.getAll(name);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // Search One
    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<GetScheduleResponse> getSchedule(@PathVariable Long scheduleId) {
        GetScheduleResponse result = scheduleService.getOne(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // Update
    @PutMapping("/schedules/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody UpdateScheduleRequest request) {

        UpdateScheduleResponse result = scheduleService.updateSchedule(scheduleId, request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    // Delete
    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/schedules/page")
    public ResponseEntity<Page<GetSchedulesPageResponse>> getSchedulesPage(
            @PageableDefault(size = 10, sort = "modifiedAt", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<GetSchedulesPageResponse> result = scheduleService.getSchedulesPage(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
