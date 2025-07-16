package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
public class UserCreateDTO {
    @NotBlank(message = "Username cannot be empty")
    private String username;
    @Email(message = "Email should be valid")
    private String email;
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
}
