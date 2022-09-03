package com.epicode.project.week_11.service;

import java.util.Optional;

import com.epicode.project.week_11.repository.UserRepository;
import com.epicode.project.week_11.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

}