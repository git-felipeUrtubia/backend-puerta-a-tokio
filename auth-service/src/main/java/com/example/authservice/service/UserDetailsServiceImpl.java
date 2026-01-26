package com.example.authservice.service;

import com.example.authservice.dto.request.LoginRequestDTO;
import com.example.authservice.dto.request.RegisterRequestDTO;
import com.example.authservice.dto.response.AuthResponseDTO;
import com.example.authservice.persistence.model.UserEntity;
import com.example.authservice.persistence.repository.UserRepository;
import com.example.authservice.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User "+ username +" not found"));

        List<GrantedAuthority> authoritiesList = new ArrayList<>();

        userEntity.getRoles()
                .forEach(role -> authoritiesList.add(new SimpleGrantedAuthority(role.getRoleEnum().name())));

        userEntity.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .forEach(permission -> authoritiesList.add(new SimpleGrantedAuthority(permission.getPermission())));

        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getIsEnabled(),
                userEntity.getAccountNonExpired(),
                userEntity.getAccountNonLocked(),
                userEntity.getCredentialsNonExpired(),
                authoritiesList
        );
    }

}
