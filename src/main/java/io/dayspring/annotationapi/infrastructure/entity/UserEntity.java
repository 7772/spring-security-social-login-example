package io.dayspring.annotationapi.infrastructure.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.*;

import io.dayspring.annotationapi.domain.type.UserRole;
import lombok.*;

@Entity
@Table(name = "user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @Getter
    private String profileImageUrl;

    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<RoleEntity> roles = new ArrayList<>();

    public UserEntity update(String name, String profileImageUrl) {
        this.name = name;
        this.profileImageUrl = profileImageUrl;

        return this;
    }

    public Optional<UserRole> getRole() {
        UserRole role = roles.isEmpty() ? null : roles.get(0).getRole();

        return Optional.ofNullable(role);
    }
}
