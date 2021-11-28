package io.dayspring.annotationapi.infrastructure.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private String name;

    @Getter
    @Column(nullable = false)
    private String email;

    @Column
    private String profileImageUrl;

    @ManyToMany
    @JoinTable(name = "user_role")
    private List<RoleEntity> roles = new ArrayList<>();

    public UserEntity update(String name, String profileImageUrl) {
        this.name = name;
        this.profileImageUrl = profileImageUrl;

        return this;
    }
}
