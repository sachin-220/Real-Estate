package com.examly.springapp.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    public String generateToken(String username) {
        logger.info("Generating token for user: {}", username);
        return "dummy-jwt-token-for-" + username;
    }

    public boolean validateToken(String token) {
        logger.info("Validating token");
        return token != null && token.startsWith("dummy-jwt-token-for-");
    }

    public String getUsernameFromToken(String token) {
        if (token != null && token.startsWith("dummy-jwt-token-for-")) {
            return token.replace("dummy-jwt-token-for-", "");
        }
        return null;
    }
}
