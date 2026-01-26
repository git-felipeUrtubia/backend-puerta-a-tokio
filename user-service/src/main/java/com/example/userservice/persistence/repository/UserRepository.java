package com.example.userservice.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.userservice.persistence.model.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.userID = :id")
    Optional<UserEntity> findUserById(@Param("id") Long id);
}
