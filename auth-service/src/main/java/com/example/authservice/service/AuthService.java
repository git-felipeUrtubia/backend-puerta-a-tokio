package com.example.authservice.service;

import com.example.authservice.dto.request.LoginRequestDTO;
import com.example.authservice.dto.request.RegisterRequestDTO;
import com.example.authservice.dto.request.UserProfileRequestDTO;
import com.example.authservice.dto.response.AuthResponseDTO;
import com.example.authservice.dto.response.UserResponseDTO;
import com.example.authservice.persistence.model.RoleEntity;
import com.example.authservice.persistence.model.RoleEnum;
import com.example.authservice.persistence.model.UserEntity;
import com.example.authservice.persistence.repository.RoleRepository;
import com.example.authservice.persistence.repository.UserRepository;
import com.example.authservice.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.management.relation.RoleNotFoundException;
import java.util.Set;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public String RegisterUser(RegisterRequestDTO req) throws RoleNotFoundException {

        RoleEntity role = roleRepository.findByRole(RoleEnum.USER).orElseThrow(() -> new RoleNotFoundException("Role: USER - no encontrado"));

        UserEntity userEntity = UserEntity.builder()
                .username(req.username())
                .password(passwordEncoder.encode(req.password()))
                .isEnabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .roles(Set.of(role))
                .build();
        userRepository.save(userEntity);

        UserProfileRequestDTO userProfileRequest = new UserProfileRequestDTO(
                userEntity.getUserID(),
                req.firstName(),
                req.lastName()
        );

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://localhost:8080/user/save-user", userProfileRequest, String.class);

//        return "Usuario guardado exitosamente";
    }

    public AuthResponseDTO LoginUser(LoginRequestDTO req) {
        String username = req.username();
        String password = req.password();

        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtils.createToken(authentication);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/find-user-{id}";
        Long id = user.getUserID();
        UserResponseDTO data = restTemplate.getForObject(url, UserResponseDTO.class, id);

        if (data == null) {
            throw new RuntimeException("No se pudo obtener la información del usuario");
        }

        return new AuthResponseDTO(username, "User logued successfully", token, true, data.userID(), data.firstName(), data.lastName());
    }

    public Authentication authenticate(String username, String password) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (userDetails == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

}
