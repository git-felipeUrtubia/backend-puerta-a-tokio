package com.example.catalogservice.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "tour")
@Builder
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tour_id")
    private Long tourID;

    @Column(nullable = false)
    private int num_tour;

    @Column(nullable = false)
    private String title;

    private String vigencia;

    @Column(nullable = false)
    private String image_card;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String route;

    @Column(nullable = false)
    private String duration;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int rating;

    @Column(name = "ref_user_id")
    private Long userID;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "tour")
    @JsonManagedReference("tour-galery")
    private List<Galery> galeries;

}
