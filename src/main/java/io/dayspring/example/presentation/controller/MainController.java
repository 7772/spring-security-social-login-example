package io.dayspring.example.presentation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.dayspring.example.domain.auth.dto.SessionUserDto;
import io.dayspring.example.domain.auth.LoggedInUser;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final HttpSession httpSession;

    @GetMapping("/")
    public String main(@LoggedInUser SessionUserDto sessionUserDto) {
        if (sessionUserDto != null) {
            return sessionUserDto.getName() + " " + sessionUserDto.getEmail();
        }

        return "Not logged in";
    }
}
