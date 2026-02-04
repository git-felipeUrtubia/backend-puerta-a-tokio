package com.example.catalogservice.dto.req;

public record InfoTourRequestDTO(String title,
                                 String subtitle,
                                 String title_desc,
                                 String description,
                                 String image_portada,
                                 String image_body,
                                 String region,
                                 String genero,
                                 String excursion,
                                 String catalog) {
}
