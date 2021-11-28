package io.dayspring.annotationapi.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.dayspring.annotationapi.domain.type.UserRole;
import io.dayspring.annotationapi.infrastructure.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByRole(UserRole role);

    default List<RoleEntity> getFreeRoles() {
        Optional<RoleEntity> roleOptional = findByRole(UserRole.FREE);

        if (roleOptional.isPresent()) {
            return List.of(roleOptional.get());
        }

        RoleEntity freeRole = RoleEntity.builder()
            .role(UserRole.FREE)
            .build();

        return List.of(save(freeRole));
    }
}
