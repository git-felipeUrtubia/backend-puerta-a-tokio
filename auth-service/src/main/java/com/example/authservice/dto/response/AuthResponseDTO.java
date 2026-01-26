package com.example.authservice.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "username",
        "message",
        "jwt",
        "status",
        "userID",
        "firstName",
        "lastName"
})
public record AuthResponseDTO(String username,
                              String message,
                              String jwt,
                              boolean status,
                              Long userID,
                              String firstName,
                              String lastName) {
}
