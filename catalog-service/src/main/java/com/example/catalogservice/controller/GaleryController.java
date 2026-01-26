package com.example.catalogservice.controller;


import com.example.catalogservice.dto.req.SaveGaleryDTO;
import com.example.catalogservice.service.GaleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog/galery")
public class GaleryController {

    @Autowired
    private GaleryService galeryService;

    @PostMapping("/create")
    public ResponseEntity<?> createGalery(@RequestBody SaveGaleryDTO req) {
        try {
            return new ResponseEntity<>(galeryService.saveGalery(req), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
