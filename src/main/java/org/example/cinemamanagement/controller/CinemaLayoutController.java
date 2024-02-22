package org.example.cinemamanagement.controller;

import org.example.cinemamanagement.dto.CinemaLayoutDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/layouts")
public class CinemaLayoutController {


    @GetMapping
    public ResponseEntity<?> getAllLayout() {
        return ResponseEntity.ok("All layout");
    }

    @PostMapping
    public ResponseEntity<?> addLayout() {
        return ResponseEntity.ok("Layout added");
    }

    @PutMapping
    public ResponseEntity<?> updateLayout() {
        return ResponseEntity.ok("Layout updated");
    }

    @DeleteMapping
    public ResponseEntity<?> deleteLayout() {
        return ResponseEntity.ok("Layout deleted");
    }

}
