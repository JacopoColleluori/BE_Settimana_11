package com.epicode.project.week_11.controller;

import java.util.Optional;

import com.epicode.project.week_11.security.model.User;
import com.epicode.project.week_11.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/user/")
@Tag(name="User")
@SecurityRequirement(name="bearerAuth")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> findById(@PathVariable(required = true) Integer id) {
        Optional<User> find = userService.findById(id);

        if (find.isPresent()) {
            return new ResponseEntity<>(find.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
