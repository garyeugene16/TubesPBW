package com.example.TugasBesar.User;

import lombok.Data;

@Data
public class User {
    private final int user_id;
    private final String username;
    private final String email;
    private final String password;
    private final String role;
}