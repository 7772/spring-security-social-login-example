package io.dayspring.annotationapi.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserRole {
    FREE("ROLE_FREE", "Free"),
    PRO("ROLE_PRO", "Pro"),
    ENTERPRISE("ROLE_ENTERPRISE", "Enterprise");

    @Getter
    private final String key;

    @Getter
    private final String title;
}
