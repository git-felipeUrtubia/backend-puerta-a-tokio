package com.example.catalogservice.dto.res;

import lombok.Builder;

import java.util.List;

@Builder
public record GaleryResponseDTO(Long id_galery,
                                String name,
                                String description,
                                String image_card,
                                List<String> image_galery) {
}
