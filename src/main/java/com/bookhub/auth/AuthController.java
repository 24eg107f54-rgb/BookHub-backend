package com.bookhub.auth;

import com.bookhub.user.AppUser;
import com.bookhub.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final UserRepository users;

    public AuthController(AuthService authService, UserRepository users) {
        this.authService = authService;
        this.users = users;
    }

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @GetMapping("/me")
    public AuthResponse me(Authentication authentication) {
        AppUser user = users.findByEmailIgnoreCase(authentication.getName()).orElseThrow();
        return new AuthResponse(null, user.getId(), user.getName(), user.getEmail(), user.getRole().name());
    }
}
