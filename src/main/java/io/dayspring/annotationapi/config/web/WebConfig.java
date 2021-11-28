package io.dayspring.annotationapi.config.web;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.dayspring.annotationapi.config.auth.LoggedInUserArgumentResolver;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final LoggedInUserArgumentResolver loggedInUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> handlerMethodArgumentResolvers) {
        handlerMethodArgumentResolvers.add(loggedInUserArgumentResolver);
    }
}
