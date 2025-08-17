package tech.sumithmeena.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    
    private String token;
    private String username;
    private String role;
    private String message;
    private boolean success;
} 