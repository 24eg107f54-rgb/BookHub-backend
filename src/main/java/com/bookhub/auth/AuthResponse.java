package com.bookhub.auth;

public record AuthResponse(String token, Long id, String name, String email, String role) {}
