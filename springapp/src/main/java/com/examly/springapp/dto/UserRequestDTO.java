package com.examly.springapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request body for updating a user profile")
public class UserRequestDTO {

    @NotBlank(message = "Username is required")
    @Schema(description = "Username of the user", example = "john_doe")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid format")
    @Schema(description = "Email address of the user", example = "john@example.com")
    private String email;

    @NotBlank(message = "Role is required")
    @Schema(description = "Role of the user (e.g., BUYER, SELLER, ADMIN)", example = "BUYER")
    private String role;

    public UserRequestDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
