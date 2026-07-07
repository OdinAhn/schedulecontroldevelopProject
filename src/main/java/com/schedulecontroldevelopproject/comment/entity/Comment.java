package com.schedulecontroldevelopproject.comment.entity;

import com.schedulecontroldevelopproject.schedule.entity.BaseEntity;
import com.schedulecontroldevelopproject.schedule.entity.Schedule;
import com.schedulecontroldevelopproject.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // N:1
    @JoinColumn(name = "schedule_id") // Comment -> Schedule
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY) // N:1
    @JoinColumn(name = "user_id") // Comment -> User
    private User user;

    private String content;

    public Comment(Schedule schedule, User user, String content) {
        this.schedule = schedule;
        this.user = user;
        this.content = content;
    }
}