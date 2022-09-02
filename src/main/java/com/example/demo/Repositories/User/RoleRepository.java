package com.example.demo.Repositories.User;

import com.example.demo.Models.User.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
