package com.example.commentsservice.controller;

import com.example.commentsservice.dto.request.CommentRequestDTO;
import com.example.commentsservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/save-comment")
    public ResponseEntity<?> saveComment(@RequestBody CommentRequestDTO req) {
        try {
            return new ResponseEntity<>(commentService.save(req), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> getAllComments() {
        try {
            return new ResponseEntity<>(commentService.findAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
