package com.epicode.project.week_11.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.epicode.project.week_11.exception.CatalogueException;
import com.epicode.project.week_11.repository.RoleRepository;
import com.epicode.project.week_11.repository.UserRepository;
import com.epicode.project.week_11.security.model.*;
import com.epicode.project.week_11.service.UserDetailsImpl;
import com.epicode.project.week_11.service.UserService;
import com.epicode.project.week_11.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userservice;

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        LoginResponse loginResponse = new LoginResponse();

        loginResponse.setToken(token);
        loginResponse.setRoles(roles);

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/signup")
    @Operation(summary="Se non si specifica il ruolo di default è user,si possono avere più ruoli")
    public ResponseEntity<?> registraUser(@RequestBody RequestRegisterUser registraUser) {

        if (userRepository.existsByEmail(registraUser.getEmail())) {
            return new ResponseEntity<String>("email già in uso!", HttpStatus.BAD_REQUEST);
        } else if (userRepository.existsByUserName(registraUser.getUserName())) {
            return new ResponseEntity<String>("username già in uso!", HttpStatus.BAD_REQUEST);
        }

        User userRegistrato = new User();
        userRegistrato.setUserName(registraUser.getUserName());
        userRegistrato.setEmail(registraUser.getEmail());
        userRegistrato.setPassword(encoder.encode(registraUser.getPassword()));
        if (registraUser.getRoles().isEmpty()) {
            Optional<Role> ruolo = roleRepository.findByRoleName(Roles.ROLE_USER);
            Set<Role> ruoli = new HashSet<>();
            ruoli.add(ruolo.get());
            userRegistrato.setRoles(ruoli);
        } else {
            Set<Role> ruoli = new HashSet<>();
            for (String set : registraUser.getRoles()) {
                switch (set.toUpperCase()) {
                    case "ADMIN":
                        Optional<Role> ruoloA = roleRepository.findByRoleName(Roles.ROLE_ADMIN);
                        ruoli.add(ruoloA.get());
                        break;
                    case "USER":
                        Optional<Role> ruoloB = roleRepository.findByRoleName(Roles.ROLE_USER);
                        ruoli.add(ruoloB.get());
                        break;
                    default:
                        throw new CatalogueException("Role not found!");

                }

            }
            userRegistrato.setRoles(ruoli);

        }
        userRepository.save(userRegistrato);
        return new ResponseEntity<>("Utente inserito con successo: " + userRegistrato.toString(), HttpStatus.CREATED);

    }



}