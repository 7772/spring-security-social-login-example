package io.dayspring.annotationapi.config.auth;

import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import io.dayspring.annotationapi.domain.auth.dto.SessionUserDto;
import io.dayspring.annotationapi.domain.auth.LoggedInUser;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoggedInUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    private final static String USER_ATTRIBUTE = "user";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isLoggedInUserAnnotation = parameter.getParameterAnnotation(LoggedInUser.class) != null;
        boolean isUserClass = SessionUserDto.class.equals(parameter.getParameterType());

        return isLoggedInUserAnnotation && isUserClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        return httpSession.getAttribute(USER_ATTRIBUTE);
    }
}
