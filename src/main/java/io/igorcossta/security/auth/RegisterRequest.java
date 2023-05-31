package io.igorcossta.security.auth;

import io.igorcossta.security.user.Role;

public record RegisterRequest(String firstName, String lastName, String email, String password, Role role) {
}
