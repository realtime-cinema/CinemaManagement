package org.example.cinemamanagement.controller;

import org.example.cinemamanagement.dto.CinemaDTO;
import org.example.cinemamanagement.dto.CinemaManagerDTO;
import org.example.cinemamanagement.payload.request.AddCinemaRequest;
import org.example.cinemamanagement.service.CinemaManagerService;
import org.example.cinemamanagement.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cinemas")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private CinemaManagerService cinemaManagerService;

    /**
     *
     *                   CRUD basic
     */
    @GetMapping
    public ResponseEntity<List<CinemaDTO>> getAllCinema() {
        return ResponseEntity.ok(cinemaService.getAllCinema());
    }


    @GetMapping("/{id}")
    public ResponseEntity<CinemaDTO> getCinema(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok(cinemaService.getCinema(id));
    }


    @PostMapping
    public ResponseEntity<CinemaDTO> addCinema(@RequestBody AddCinemaRequest addCinemaRequest) {
        return ResponseEntity.ok(cinemaService.addCinema(addCinemaRequest));
    }


    @PutMapping
    public ResponseEntity<?> updateCinema(@RequestBody CinemaDTO cinemaDTO) {
        cinemaService.updateCinema(cinemaDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Cinema updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCinema(@PathVariable(value = "id") UUID id) {
        cinemaService.deleteCinema(id);
        return ResponseEntity.status(HttpStatus.OK).body("Cinema deleted successfully");
    }


    /**
     *
     *                       Another
     */
    @GetMapping("/{id}/managers")
    public ResponseEntity<List<CinemaManagerDTO>> getAllManagerFromCinema(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok(cinemaManagerService.getAllCinemaManagerFromCinema(id));
    }
}
