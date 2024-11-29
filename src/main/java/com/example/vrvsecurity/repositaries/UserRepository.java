package com.example.vrvsecurity.repositaries;

import com.example.vrvsecurity.models.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
