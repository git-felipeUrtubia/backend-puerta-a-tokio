package com.example.catalogservice.controller;


import com.example.catalogservice.dto.req.TourRequestDTO;
import com.example.catalogservice.dto.res.TourResponseDTO;
import com.example.catalogservice.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog/tour")
public class TourController {

    @Autowired
    private TourService tourService;

    @PostMapping("/create")
    public ResponseEntity<?> createTour(@RequestBody TourRequestDTO req) {

        try {
            return new ResponseEntity<>(tourService.saveTour(req), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/find-all")
    public ResponseEntity<?> getAllTours() {
        try {
            return new ResponseEntity<>(tourService.findAllTours(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
