package io.igorcossta.security.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    USER_READ("user:read"),
    USER_CREATE("user:create"),
    USER_DELETE("user:delete");

    @Getter
    private final String permission;
}
