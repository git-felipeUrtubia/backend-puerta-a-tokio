package com.example.commentsservice.persistence.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentID;

    @Column(nullable = false)
    private String comment;

    private int rating;

    @Column(name = "date_created_at", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateCreatedAt;

    @Column(name = "time_created_at", nullable = false)
    private LocalTime timeCreatedAt;

    @Column(nullable = false, name = "ref_user_id")
    private Long userID;

}
