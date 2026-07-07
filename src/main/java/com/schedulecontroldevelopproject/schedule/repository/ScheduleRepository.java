package com.schedulecontroldevelopproject.schedule.repository;

import com.schedulecontroldevelopproject.schedule.dto.GetSchedulesPageResponse;
import com.schedulecontroldevelopproject.schedule.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByUser_UsernameContaining(String username); // 자동으로 쿼리 만들어주는 기능

    @Query("SELECT new com.schedulecontroldevelopproject.schedule.dto.GetSchedulesPageResponse(" +
            "s.id, s.user.username, s.title, s.content, COUNT(c), s.createdAt, s.modifiedAt) " +
            "FROM Schedule s LEFT JOIN Comment c ON c.schedule = s " +
            "GROUP BY s.id, s.user.username, s.title, s.content, s.createdAt, s.modifiedAt")
    Page<GetSchedulesPageResponse> findSchedulesWithCommentCount(Pageable pageable);
}