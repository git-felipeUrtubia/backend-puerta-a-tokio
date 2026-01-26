package com.example.authservice.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userID;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, name = "is_enabled")
    private Boolean isEnabled;

    @Column(nullable = false, name = "account_non_expired")
    private Boolean accountNonExpired;

    @Column(nullable = false, name = "account_non_locked")
    private Boolean accountNonLocked;

    @Column(nullable = false, name = "credentials_non_expired")
    private Boolean credentialsNonExpired;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "fk_user_id"), inverseJoinColumns = @JoinColumn(name = "fk_role_id"))
    private Set<RoleEntity> roles;

}
