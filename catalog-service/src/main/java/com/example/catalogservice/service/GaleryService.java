package com.example.catalogservice.service;

import com.example.catalogservice.dto.req.SaveGaleryDTO;
import com.example.catalogservice.dto.res.GaleryResponseDTO;
import com.example.catalogservice.model.Galery;
import com.example.catalogservice.repository.GaleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GaleryService {

    @Autowired
    private GaleryRepository galeryRepo;

    public GaleryResponseDTO saveGalery(SaveGaleryDTO req) {
        Galery galery = galeryRepo.save(Galery.builder()
                        .name(req.name())
                        .description(req.description())
                        .image_card(req.image_card())
                        .image_galery(req.image_galery())
                .build());
        return GaleryResponseDTO.builder()
                .id_galery(galery.getId_galery())
                .name(req.name())
                .description(req.description())
                .image_card(req.image_card())
                .image_galery(req.image_galery())
                .build();
    }

}
