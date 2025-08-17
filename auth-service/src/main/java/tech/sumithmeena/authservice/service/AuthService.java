package tech.sumithmeena.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.sumithmeena.authservice.dto.AuthRequest;
import tech.sumithmeena.authservice.dto.AuthResponse;
import tech.sumithmeena.authservice.dto.RegisterRequest;
import tech.sumithmeena.authservice.model.User;
import tech.sumithmeena.authservice.repository.UserRepository;
import tech.sumithmeena.authservice.util.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthResponse register(RegisterRequest request) {
        // Check if username already exists
        if (userRepository.existsByUsername(request.getUsername())) {
            return new AuthResponse(null, null, null, "Username already exists", false);
        }

        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            return new AuthResponse(null, null, null, "Email already exists", false);
        }

        // Create new user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");
        user.setEnabled(true);

        userRepository.save(user);

        // Generate token
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

        return new AuthResponse(token, user.getUsername(), user.getRole(), "Registration successful", true);
    }

    public AuthResponse login(AuthRequest request) {
        // Find user by username
        User user = userRepository.findByUsername(request.getUsername())
                .orElse(null);

        if (user == null) {
            return new AuthResponse(null, null, null, "Invalid username or password", false);
        }

        // Check password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new AuthResponse(null, null, null, "Invalid username or password", false);
        }

        // Check if user is enabled
        if (!user.isEnabled()) {
            return new AuthResponse(null, null, null, "Account is disabled", false);
        }

        // Generate token
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

        return new AuthResponse(token, user.getUsername(), user.getRole(), "Login successful", true);
    }

    public boolean validateToken(String token, String username) {
        return jwtUtil.validateToken(token, username);
    }

    public String getUsernameFromToken(String token) {
        return jwtUtil.extractUsername(token);
    }
} 