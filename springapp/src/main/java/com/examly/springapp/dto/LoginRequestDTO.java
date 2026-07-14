package com.examly.springapp.dto;

import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request body for user login")
public class LoginRequestDTO {

    @NotBlank(message = "Username is required")
    @Schema(description = "Username of the user", example = "john_doe")
    private String username;

    @NotBlank(message = "Password is required")
    @Schema(description = "Password of the user", example = "Password123")
    private String password;

    public LoginRequestDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
