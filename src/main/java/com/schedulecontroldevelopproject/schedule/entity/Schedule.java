package com.schedulecontroldevelopproject.schedule.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String title;
    private String content;


    public Schedule(String name, String title, String content) {
        this.name = name;
        this.title = title;
        this.content = content;
    }

    public void update(String name, String title, String content) {
        this.name = name;
        this.title = title;
        this.content = content;
    }
}
