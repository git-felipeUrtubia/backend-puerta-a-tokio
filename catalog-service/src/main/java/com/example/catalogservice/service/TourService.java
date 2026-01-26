package com.example.catalogservice.service;

import com.example.catalogservice.dto.req.TourRequestDTO;
import com.example.catalogservice.dto.res.TourResponseDTO;
import com.example.catalogservice.model.Galery;
import com.example.catalogservice.model.Tour;
import com.example.catalogservice.repository.GaleryRepository;
import com.example.catalogservice.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourService {

    @Autowired
    private TourRepository tourRepo;

    @Autowired
    private GaleryRepository galeryRepo;

    public TourResponseDTO saveTour(TourRequestDTO req) {

        List<Galery> galeries = req.getGaleries().stream()
                        .map(g -> galeryRepo.findById(g.id_galery())
                                .orElseThrow(() -> new RuntimeException("Galery not found"))
                        ).toList();

        Tour tour = tourRepo.save(Tour.builder()
                        .num_tour(req.getNum_tour())
                        .title(req.getTitle())
                        .vigencia(req.getVigencia())
                        .image_card(req.getImage_card())
                        .destination(req.getDestination())
                        .description(req.getDescription())
                        .route(req.getRoute())
                        .duration(req.getDuration())
                        .price(req.getPrice())
                        .rating(req.getRating())
                        .galeries(galeries)
                .build());

        List<String> galeryList = galeries.stream()
                .flatMap(g -> g.getImage_galery().stream()).toList();

        return TourResponseDTO.builder()
                .id_tour(tour.getTourID())
                .num_tour(req.getNum_tour())
                .title(req.getTitle())
                .vigencia(req.getVigencia())
                .image_card(req.getImage_card())
                .destination(req.getDestination())
                .description(req.getDescription())
                .route(req.getRoute())
                .duration(req.getDuration())
                .price(req.getPrice())
                .rating(req.getRating())
                .galeries(galeryList)
                .build();

    }

    public List<TourResponseDTO> findAllTours() {
        List<Tour> tours = tourRepo.findAll();

        List<String> galeryList = galeryRepo.findAll().stream()
                .flatMap(g -> g.getImage_galery().stream()).toList();

        return tours.stream()
                .map(t -> TourResponseDTO.builder()
                        .id_tour(t.getTourID())
                        .num_tour(t.getNum_tour())
                        .title(t.getTitle())
                        .vigencia(t.getVigencia())
                        .image_card(t.getImage_card())
                        .destination(t.getDestination())
                        .description(t.getDescription())
                        .route(t.getRoute())
                        .duration(t.getDuration())
                        .price(t.getPrice())
                        .rating(t.getRating())
                        .galeries(galeryList)
                        .build())
                .toList();
    }

}
