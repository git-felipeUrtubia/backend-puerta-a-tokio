package com.example.authservice;

import com.example.authservice.persistence.model.PermissionEntity;
import com.example.authservice.persistence.model.RoleEntity;
import com.example.authservice.persistence.model.RoleEnum;
import com.example.authservice.persistence.model.UserEntity;
import com.example.authservice.persistence.repository.UserRepository;
import com.example.authservice.service.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class AuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {

            PermissionEntity createPermission = PermissionEntity.builder()
                    .permission("CREATE")
                    .build();

            PermissionEntity readPermission = PermissionEntity.builder()
                    .permission("READ")
                    .build();

            PermissionEntity updatePermission = PermissionEntity.builder()
                    .permission("UPDATE")
                    .build();

            PermissionEntity deletePermission = PermissionEntity.builder()
                    .permission("DELETE")
                    .build();


            RoleEntity roleAdmin = RoleEntity.builder()
                    .roleEnum(RoleEnum.ADMIN)
                    .permissions(Set.of(createPermission, readPermission, updatePermission, deletePermission))
                    .build();

            RoleEntity roleUser = RoleEntity.builder()
                    .roleEnum(RoleEnum.USER)
                    .permissions(Set.of(createPermission, readPermission))
                    .build();

            UserEntity createTestAdmin = UserEntity.builder()
                    .username("testAdmin@gmail.com")
                    .password(passwordEncoder.encode("1234"))
                    .isEnabled(true)
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .roles(Set.of(roleAdmin))
                    .build();

            UserEntity createTestUser = UserEntity.builder()
                    .username("testUser@gmail.com")
                    .password(passwordEncoder.encode("1234"))
                    .isEnabled(true)
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .roles(Set.of(roleUser))
                    .build();

            userRepository.saveAll(Set.of(createTestAdmin, createTestUser));

        };
    }

}
