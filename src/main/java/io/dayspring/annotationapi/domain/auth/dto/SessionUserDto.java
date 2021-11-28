package io.dayspring.annotationapi.domain.auth.dto;

import java.io.Serializable;

import io.dayspring.annotationapi.infrastructure.entity.UserEntity;
import lombok.Getter;

@Getter
public class SessionUserDto implements Serializable {

   private final String name;
   private final String email;
   private final String profileImageUrl;

   public SessionUserDto(UserEntity user) {
       this.name = user.getName();
       this.email = user.getEmail();
       this.profileImageUrl = user.getProfileImageUrl();
   }
}
