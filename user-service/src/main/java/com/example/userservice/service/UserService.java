package com.example.userservice.service;

import com.example.userservice.dto.request.SaveUserRquestDTO;
import com.example.userservice.dto.response.NamesResponseDTO;
import com.example.userservice.dto.response.UserResponseDTO;
import com.example.userservice.persistence.model.UserEntity;
import com.example.userservice.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String saveUser(SaveUserRquestDTO req) {

        userRepository.save(UserEntity.builder()
                .userID(req.userID())
                .firstName(req.firstName())
                .lastName(req.lastName())
                .build());

        return "User saved successfully";

    }

    public List<UserResponseDTO> findAllUsers() {

        List<UserEntity> userEntities = userRepository.findAll();

        return userEntities.stream()
                .map(user -> new UserResponseDTO(user.getUserID(), user.getFirstName(), user.getLastName())).toList();

    }

    public UserResponseDTO findUserByID(Long id) {
        UserEntity res = userRepository.findUserById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserResponseDTO(
                res.getUserID(),
                res.getFirstName(),
                res.getLastName()
        );
    }

}
