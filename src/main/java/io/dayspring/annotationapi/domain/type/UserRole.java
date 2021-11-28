package io.dayspring.annotationapi.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserRole {
    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "회원");

    @Getter
    private final String key;

    @Getter
    private final String title;
}
