package com.example.authservice.controller;

import com.example.authservice.dto.request.LoginRequestDTO;
import com.example.authservice.dto.request.RegisterRequestDTO;
import com.example.authservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> RegisterUser(@RequestBody RegisterRequestDTO req) {
        try {
            return new ResponseEntity<>(authService.RegisterUser(req), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> LoginUser(@RequestBody LoginRequestDTO req) {
        try {
            return new ResponseEntity<>(authService.LoginUser(req), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/saludo")
    public String saludo() {
        return "Hola Mundo con token";
    }

}
