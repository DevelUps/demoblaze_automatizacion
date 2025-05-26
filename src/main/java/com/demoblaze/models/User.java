package com.demoblaze.models;

import java.util.UUID;

public class User {
    private String username;
    private String password;

    public User() {
        this.username = "user_" + UUID.randomUUID().toString().substring(0, 8);
        this.password = "Password123!";
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
} 