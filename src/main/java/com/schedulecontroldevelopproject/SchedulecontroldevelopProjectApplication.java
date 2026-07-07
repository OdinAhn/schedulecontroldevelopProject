package com.schedulecontroldevelopproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // JpaAudit 사용
public class SchedulecontroldevelopProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchedulecontroldevelopProjectApplication.class, args);
    }

}
