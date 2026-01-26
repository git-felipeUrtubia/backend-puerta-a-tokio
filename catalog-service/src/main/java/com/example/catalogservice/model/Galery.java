package com.example.catalogservice.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "galery")
@Builder
public class Galery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_galery;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String image_card;

    @Column(nullable = false, name = "image_galery")
    private List<String> image_galery;

    @ManyToOne()
    @JsonBackReference("tour-galery")
    Tour tour;

}
