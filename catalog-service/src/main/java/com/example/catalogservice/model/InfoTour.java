package com.example.catalogservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "info_tour")
@Builder
public class InfoTour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long info_id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String subtitle;

    @Column(nullable = false)
    private String title_desc;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String image_portada;

    @Column(nullable = false)
    private String image_body;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String genero;

    @Column(nullable = false)
    private String excursion;

    @Column(nullable = false, unique = true)
    private String catalog;
}
