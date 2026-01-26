package com.example.catalogservice.dto.req;

import java.util.List;

public record SaveGaleryDTO(String name,
                            String description,
                            String image_card,
                            List<String> image_galery) {
}
