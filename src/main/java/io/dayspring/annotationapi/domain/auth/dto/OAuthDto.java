package io.dayspring.annotationapi.domain.auth.dto;

import io.dayspring.annotationapi.domain.type.UserRole;
import io.dayspring.annotationapi.infrastructure.entity.RoleEntity;
import io.dayspring.annotationapi.infrastructure.entity.UserEntity;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class OAuthDto {
   private Map<String, Object> attributes;
   private String nameAttributeKey;
   private String name;
   private String email;
   private String profileImageUrl;

   @Builder
   public OAuthDto(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String profileImageUrl) {
       this.attributes = attributes;
       this.nameAttributeKey= nameAttributeKey;
       this.name = name;
       this.email = email;
       this.profileImageUrl = profileImageUrl;
   }

   public static OAuthDto of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
       return ofGoogle(userNameAttributeName, attributes);
   }

   private static OAuthDto ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
       return OAuthDto.builder()
               .name((String) attributes.get("name"))
               .email((String) attributes.get("email"))
               .profileImageUrl((String) attributes.get("profileImageUrl"))
               .attributes(attributes)
               .nameAttributeKey(userNameAttributeName)
               .build();
   }

   public UserEntity toUserEntity() {
       RoleEntity role = RoleEntity.builder()
           .role(UserRole.FREE)
           .build();

       return UserEntity.builder()
               .name(name)
               .email(email)
               .profileImageUrl(profileImageUrl)
               .roles(List.of(role))
               .build();
   }
}
