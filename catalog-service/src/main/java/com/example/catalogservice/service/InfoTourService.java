package com.example.catalogservice.service;

import com.example.catalogservice.dto.req.InfoTourRequestDTO;
import com.example.catalogservice.dto.res.InfoTourResponseDTO;
import com.example.catalogservice.model.InfoTour;
import com.example.catalogservice.repository.InfoTourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InfoTourService {

    @Autowired
    private InfoTourRepository infoTourRepository;

    public InfoTourResponseDTO saveInfoTour(InfoTourRequestDTO req) {

        InfoTour it = infoTourRepository.save(InfoTour.builder()
                        .title(req.title())
                        .subtitle(req.subtitle())
                        .title_desc(req.title_desc())
                        .description(req.description())
                        .image_portada(req.image_portada())
                        .image_body(req.image_body())
                        .region(req.region())
                        .genero(req.genero())
                        .excursion(req.excursion())
                        .catalog(req.catalog())
                .build());

        return new InfoTourResponseDTO(
                it.getInfo_id(),
                it.getTitle(),
                it.getSubtitle(),
                it.getTitle_desc(),
                it.getDescription(),
                it.getImage_portada(),
                it.getImage_body(),
                it.getRegion(),
                it.getGenero(),
                it.getExcursion(),
                it.getCatalog()
        );

    }

    public List<InfoTourResponseDTO> findAllInfoTours() {
        List<InfoTourResponseDTO> listInfo = new ArrayList<>();

        infoTourRepository.findAll()
                .forEach(it -> listInfo.add(new InfoTourResponseDTO(
                        it.getInfo_id(),
                        it.getTitle(),
                        it.getSubtitle(),
                        it.getTitle_desc(),
                        it.getDescription(),
                        it.getImage_portada(),
                        it.getImage_body(),
                        it.getRegion(),
                        it.getGenero(),
                        it.getExcursion(),
                        it.getCatalog()
                )));

        return listInfo;
    }

}
