package com.epicode.project.week_11.repository;

import java.util.Optional;

import com.epicode.project.week_11.security.model.Role;
import com.epicode.project.week_11.security.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByRoleName(Roles role);
}