package com.example.catalogservice.controller;

import com.example.catalogservice.dto.req.InfoTourRequestDTO;
import com.example.catalogservice.service.InfoTourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/catalog/info")
public class InfoTourController {

    @Autowired
    private InfoTourService infoTourService;

    @PostMapping("/save-info")
    public ResponseEntity<?> saveInfo(@RequestBody InfoTourRequestDTO req) {
        try {
            return new ResponseEntity<>(infoTourService.saveInfoTour(req), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> getAllInfo() {
        try {
            return new ResponseEntity<>(infoTourService.findAllInfoTours(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
