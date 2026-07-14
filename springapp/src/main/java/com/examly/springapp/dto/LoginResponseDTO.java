package com.examly.springapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response returned after successful authentication")
public class LoginResponseDTO {

    @Schema(description = "JWT authentication token", example = "dummy-jwt-token-for-john_doe")
    private String token;

    @Schema(description = "Username of the authenticated user", example = "john_doe")
    private String username;

    @Schema(description = "Role of the authenticated user", example = "BUYER")
    private String role;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(String token, String username, String role) {
        this.token = token;
        this.username = username;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
