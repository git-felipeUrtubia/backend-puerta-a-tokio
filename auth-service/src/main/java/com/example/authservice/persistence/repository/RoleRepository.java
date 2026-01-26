package com.example.authservice.persistence.repository;

import com.example.authservice.persistence.model.RoleEntity;
import com.example.authservice.persistence.model.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    @Query("SELECT r FROM RoleEntity r WHERE r.roleEnum = :roleEnum")
    Optional<RoleEntity> findByRole(@Param("roleEnum") RoleEnum roleEnum);
}
