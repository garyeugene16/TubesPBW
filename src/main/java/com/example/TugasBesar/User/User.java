package com.example.TugasBesar.User;

import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
@AllArgsConstructor
public class User {
    @NotBlank(message = "Username cannot be empty")
    @Size(min = 4, max = 30, message = "Username min length is 4 characters")
    private String username;
    
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 4, max = 60, message = "Password min length is 4 characters")
    private String password;

    private String confirmpassword;

    private String role;

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 4, max = 50, message = "Name min length is 4 and 50")
    private String name;
}