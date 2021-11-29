package io.dayspring.annotationapi.application.service;

import io.dayspring.annotationapi.domain.auth.vo.OAuthVo;
import io.dayspring.annotationapi.domain.auth.dto.SessionUserDto;
import io.dayspring.annotationapi.domain.type.UserRole;
import io.dayspring.annotationapi.infrastructure.entity.UserEntity;
import io.dayspring.annotationapi.infrastructure.repository.RoleRepository;
import io.dayspring.annotationapi.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

   private final UserRepository userRepository;
   private final RoleRepository roleRepository;
   private final HttpSession httpSession;

   @Override
   public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
       OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
       OAuth2User oAuth2User = delegate.loadUser(userRequest);

       String registrationId = userRequest.getClientRegistration().getRegistrationId();
       String userNameAttributeName = userRequest.getClientRegistration()
           .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

       OAuthVo attributes = OAuthVo.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

       UserEntity user = saveOrUpdate(attributes);
       httpSession.setAttribute("user", new SessionUserDto(user));

       UserRole userRole = user.getRole().orElse(UserRole.FREE);

       return new DefaultOAuth2User(
               Collections.singleton(new SimpleGrantedAuthority(userRole.getKey())),
               attributes.getAttributes(),
               attributes.getNameAttributeKey());
   }

   private UserEntity saveOrUpdate(OAuthVo oAuthVo) {
       UserEntity user = userRepository.findByEmail(oAuthVo.getEmail())
           .map(entity -> entity.update(oAuthVo.getName(), oAuthVo.getProfileImageUrl()))
           .orElse(oAuthVo.toUserEntity(roleRepository.getFreeRoles()));

       return userRepository.save(user);
   }
}
