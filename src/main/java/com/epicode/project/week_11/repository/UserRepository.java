package com.epicode.project.week_11.repository;

import java.util.Optional;

import com.epicode.project.week_11.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findById(Integer id);
    public Optional<User> findByUserName(String userName);
    public boolean existsByEmail(String email);
    public boolean existsByUserName(String userName);
}
