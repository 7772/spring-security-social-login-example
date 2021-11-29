package io.dayspring.annotationapi.domain.auth.vo;

import io.dayspring.annotationapi.domain.type.OAuthProvider;
import io.dayspring.annotationapi.infrastructure.entity.RoleEntity;
import io.dayspring.annotationapi.infrastructure.entity.UserEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
@Getter
public class OAuthVo {
   private Map<String, Object> attributes;
   private String nameAttributeKey;
   private String name;
   private String email;
   private String profileImageUrl;

   private final static String ID_KEY = "id";

   @Builder
   public OAuthVo(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String profileImageUrl) {
       this.attributes = attributes;
       this.nameAttributeKey = nameAttributeKey;
       this.name = name;
       this.email = email;
       this.profileImageUrl = profileImageUrl;
   }

   public static OAuthVo of(String registrationId, String userNameAttributeKey, Map<String, Object> attributes) {
       if (OAuthProvider.NAVER.getValue().equals(registrationId)) {
           return ofNaver(ID_KEY, attributes);
       } else if (OAuthProvider.GOOGLE.getValue().equals(registrationId)) {
           return ofGoogle(userNameAttributeKey, attributes);
       } else {
           // TODO: Custom Exception
           throw new RuntimeException("Not handled registrationId : " + registrationId);
       }
   }

   private static OAuthVo ofGoogle(String userNameAttributeKey, Map<String, Object> attributes) {
       return OAuthVo.builder()
               .name((String) attributes.get("name"))
               .email((String) attributes.get("email"))
               .profileImageUrl((String) attributes.get("profileImageUrl"))
               .attributes(attributes)
               .nameAttributeKey(userNameAttributeKey)
               .build();
   }

   private static OAuthVo ofNaver(String userNameAttributeKey, Map<String, Object> attributes) {
       Map<String, Object> response = (Map<String, Object>) attributes.get("response");

       return OAuthVo.builder()
               .name((String) response.get("name"))
               .email((String) response.get("email"))
               .profileImageUrl((String) response.get("profile_image"))
               .attributes(response)
               .nameAttributeKey(userNameAttributeKey)
               .build();
   }

   public UserEntity toUserEntity(List<RoleEntity> roles) {
       return UserEntity.builder()
               .name(name)
               .email(email)
               .profileImageUrl(profileImageUrl)
               .roles(roles)
               .build();
   }
}
