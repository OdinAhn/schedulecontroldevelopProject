package com.schedulecontroldevelopproject.schedule.repository;

import com.schedulecontroldevelopproject.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByNameContaining(String name); // 자동으로 쿼리를 만들어주는 기능
}
