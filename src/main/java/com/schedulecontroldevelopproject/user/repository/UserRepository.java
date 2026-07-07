package com.schedulecontroldevelopproject.user.repository;

import com.schedulecontroldevelopproject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}