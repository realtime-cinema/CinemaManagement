package org.example.cinemamanagement.controller;

import org.example.cinemamanagement.dto.CinemaDTO;
import org.example.cinemamanagement.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cinema")
public class ManagerCinemaController {

    CinemaService cinemaService;

    @Autowired
    public ManagerCinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/get-all-cinema")
    public ResponseEntity<List<CinemaDTO>> getAllCinema() {
        return ResponseEntity.ok(cinemaService.getAllCinema());
    }

    @PostMapping("/add-cinema")
    public ResponseEntity<CinemaDTO> addCinema(@RequestBody CinemaDTO cinemaDTO) {
        return ResponseEntity.ok(cinemaService.addCinema(cinemaDTO));
    }

    @PutMapping("/update-cinema")
    public ResponseEntity<?> updateCinema(@RequestBody CinemaDTO cinemaDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(cinemaService.updateCinema(cinemaDTO));
    }

    @DeleteMapping("/delete-cinema/{id}")
    public ResponseEntity<?> deleteCinema(@PathVariable UUID id) {
        cinemaService.deleteCinema(id);
        return ResponseEntity.status(HttpStatus.OK).body("Cinema deleted successfully");
    }

    @GetMapping("/get-all-layout")
    public ResponseEntity<?> getAllLayout() {
        return ResponseEntity.ok("All layout");
    }

    @PostMapping("/add-layout")
    public ResponseEntity<?> addLayout() {
        return ResponseEntity.ok("Layout added");
    }

    @PutMapping("/update-layout")
    public ResponseEntity<?> updateLayout() {
        return ResponseEntity.ok("Layout updated");
    }

    @DeleteMapping("/delete-layout")
    public ResponseEntity<?> deleteLayout() {
        return ResponseEntity.ok("Layout deleted");
    }

    @GetMapping("/get-all-room")
    public ResponseEntity<?> getAllRoom() {
        return ResponseEntity.ok("All room");
    }

    @PostMapping("/add-room")
    public ResponseEntity<?> addRoom() {
        return ResponseEntity.ok("Room added");
    }

    @PutMapping("/update-room")
    public ResponseEntity<?> updateRoom() {
        return ResponseEntity.ok("Room updated");
    }

    @DeleteMapping("/delete-room")
    public ResponseEntity<?> deleteRoom() {
        return ResponseEntity.ok("Room deleted");
    }


}
