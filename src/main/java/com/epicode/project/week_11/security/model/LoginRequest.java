package com.epicode.project.week_11.security.model;

import lombok.Data;

@Data
public class LoginRequest {

    private String userName;
    private String password;

}
