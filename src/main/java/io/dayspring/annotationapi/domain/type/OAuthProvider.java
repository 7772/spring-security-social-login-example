package io.dayspring.annotationapi.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OAuthProvider {
    NAVER("naver"),
    GOOGLE("google");

    @Getter
    private final String value;
}
