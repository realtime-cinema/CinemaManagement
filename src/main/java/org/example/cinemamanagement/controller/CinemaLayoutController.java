package org.example.cinemamanagement.controller;

import org.example.cinemamanagement.dto.CinemaLayoutDTO;
import org.example.cinemamanagement.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/layouts")
public class CinemaLayoutController {

    @Autowired
    private CinemaService cinemaService;

    @GetMapping("/get-all-layout")
    public ResponseEntity<List<CinemaLayoutDTO>> getAllLayout() {
        return ResponseEntity.ok(cinemaService.getAllCinemaLayout());
    }

    @PostMapping("/add-layout/{idCinema}")
    public ResponseEntity<?> addLayout(@PathVariable UUID idCinema, @RequestBody CinemaLayoutDTO cinemaLayoutDTO) {
        if (idCinema == null) {
            return ResponseEntity.badRequest().body("Id is null");
        }
        return ResponseEntity.ok(cinemaService.addCinemaLayout(idCinema, cinemaLayoutDTO));
    }

    @PutMapping("/update-layout")
    public ResponseEntity<?> updateLayout() {
        return ResponseEntity.ok("Layout updated");
    }

    @DeleteMapping("/delete-layout")
    public ResponseEntity<?> deleteLayout() {
        return ResponseEntity.ok("Layout deleted");
    }
}
