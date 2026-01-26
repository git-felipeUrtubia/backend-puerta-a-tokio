package com.example.catalogservice.dto.req;

import lombok.Data;

import java.util.List;

@Data
public class TourRequestDTO {

    private int num_tour;
    private String title;
    private String vigencia;
    private String image_card;
    private String destination;
    private String description;
    private String route;
    private String duration;
    private int price;
    private int rating;
    private List<GaleryRequestDTO> galeries;

}
