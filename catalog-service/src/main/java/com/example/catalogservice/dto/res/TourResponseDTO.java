package com.example.catalogservice.dto.res;

import com.example.catalogservice.dto.req.GaleryRequestDTO;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@JsonPropertyOrder({
        "id_tour",
        "num_tour",
        "title",
        "image_card",
        "destination",
        "description",
        "route",
        "duration",
        "price",
        "rating",
        "galeries"
})
@Builder
public record TourResponseDTO(Long id_tour,
                              int num_tour,
                              String title,
                              String vigencia,
                              String image_card,
                              String destination,
                              String description,
                              String route,
                              String duration,
                              int price,
                              int rating,
                              List<String> galeries) {


}
