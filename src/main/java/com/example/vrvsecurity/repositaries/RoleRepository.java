package com.example.vrvsecurity.repositaries;

import com.example.vrvsecurity.models.Role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
