package io.dayspring.annotationapi.presentation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.dayspring.annotationapi.domain.auth.dto.SessionUserDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final HttpSession httpSession;

    @GetMapping("/")
    public String main() {
        SessionUserDto sessionUserDto = (SessionUserDto) httpSession.getAttribute("user");

        if (sessionUserDto != null) {
            return sessionUserDto.getName() + " " + sessionUserDto.getEmail();
        }

        return "Not logged in";
    }
}
