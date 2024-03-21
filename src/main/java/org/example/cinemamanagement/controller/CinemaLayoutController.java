package org.example.cinemamanagement.controller;

import org.example.cinemamanagement.dto.CinemaLayoutDTO;
import org.example.cinemamanagement.payload.request.AddCinemaLayoutRequest;
import org.example.cinemamanagement.service.CinemaLayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/layouts")
public class CinemaLayoutController {



    CinemaLayoutService cinemaLayoutService;

    @Autowired
    public CinemaLayoutController(CinemaLayoutService cinemaLayoutService) {
        this.cinemaLayoutService = cinemaLayoutService;
    }

    @GetMapping
    public ResponseEntity<List<CinemaLayoutDTO>> getAllLayout() {
        return ResponseEntity.ok(cinemaLayoutService.getAllCinemaLayout());
    }

    @PostMapping
    public ResponseEntity<CinemaLayoutDTO> addLayout(@RequestBody AddCinemaLayoutRequest cinemaLayoutRequest) {
        return ResponseEntity.ok(cinemaLayoutService.addCinemaLayout(cinemaLayoutRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLayout(@PathVariable UUID id, @RequestBody CinemaLayoutDTO cinemaLayoutDTO) {
        return ResponseEntity.ok(cinemaLayoutService.updateCinemaLayout(id,cinemaLayoutDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLayout(@PathVariable UUID id) {
        cinemaLayoutService.deleteCinemaLayout(id);
        return ResponseEntity.ok("Cinema layout deleted successfully!");
    }

}
