package io.igorcossta.security.auth;

public record AuthenticationRequest(String email, String password) {
}
