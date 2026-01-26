package com.example.commentsservice.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public record CommentResponseDTO(Long commentID,
                                 String comment,
                                 int rating,
                                 @JsonFormat(pattern = "dd-MM-yyyy")
                                 LocalDate dateCreatedAt,
                                 LocalTime timeCreatedAt,
                                 String firstName,
                                 String lastName) {
}
