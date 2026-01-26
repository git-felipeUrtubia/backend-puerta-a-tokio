package com.example.commentsservice.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;


public record CommentRequestDTO(String comment,
                                int rating,
                                @JsonFormat(pattern = "dd-MM-yyyy")
                                LocalDate dateCreatedAt,
                                LocalTime timeCreatedAt,
                                Long userID) {
}
