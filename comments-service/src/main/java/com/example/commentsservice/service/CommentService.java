package com.example.commentsservice.service;

import com.example.commentsservice.dto.request.CommentRequestDTO;
import com.example.commentsservice.dto.response.CommentResponseDTO;
import com.example.commentsservice.dto.response.UsersDTO;
import com.example.commentsservice.persistence.model.CommentEntity;
import com.example.commentsservice.persistence.repository.CommentRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public CommentEntity save(CommentRequestDTO req) {
        return commentRepository.save(CommentEntity.builder()
                .comment(req.comment())
                .rating(req.rating())
                .dateCreatedAt(req.dateCreatedAt())
                .timeCreatedAt(req.timeCreatedAt())
                .userID(req.userID())
                .build());
    }

    public List<CommentResponseDTO> findAll() {

        List<CommentResponseDTO> res = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/find-all-users";


        // 1. CAPTURAR EL TOKEN DE LA PETICIÓN ACTUAL
        // Esto agarra el "Authorization: Bearer ..." que envió Postman
        HttpServletRequest currentRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = currentRequest.getHeader("Authorization");

        // 2. PREPARAR LOS HEADERS PARA EL VIAJE
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token); // Le pegamos el token al nuevo request
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // 3. HACER LA LLAMADA USANDO 'exchange' (Para enviar headers)
        // Nota: Usamos ParameterizedTypeReference para mapear correctamente la lista
        ResponseEntity<List<UsersDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<UsersDTO>>() {
                }
        );

        List<UsersDTO> users = response.getBody();

        commentRepository.findAll()
                .forEach(c -> {

                    users.forEach((u) -> {
                        if (c.getUserID().equals(u.userID())) {
                            res.add(new CommentResponseDTO(
                                    c.getUserID(),
                                    c.getComment(),
                                    c.getRating(),
                                    c.getDateCreatedAt(),
                                    c.getTimeCreatedAt(),
                                    u.firstName(),
                                    u.lastName()
                            ));
                        }
                    });

                });


        return res;
    }


}
