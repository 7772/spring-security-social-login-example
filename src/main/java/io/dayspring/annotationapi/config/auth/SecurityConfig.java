package io.dayspring.annotationapi.config.auth;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import io.dayspring.annotationapi.application.service.CustomOAuth2UserService;
import io.dayspring.annotationapi.domain.type.UserRole;
import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .authorizeRequests()
            .antMatchers("/", "/css/**", "/images/**", "/js/**").permitAll()
            .antMatchers("/api/**").hasAnyRole(
                UserRole.FREE.name(),
                UserRole.PRO.name(),
                UserRole.ENTERPRISE.name()
            )
            .anyRequest().authenticated()
            .and()
                .logout().logoutSuccessUrl("/")
            .and()
                .oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);
    }
}
