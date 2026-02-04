package com.example.catalogservice.dto.res;

public record InfoTourResponseDTO(Long info_id,
                                  String title,
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
