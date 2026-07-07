package com.schedulecontroldevelopproject.user.repository;

import com.schedulecontroldevelopproject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email); // 있을수도 있고 없으면 null을 다루지 않고 orElseThrow로 던진다
}