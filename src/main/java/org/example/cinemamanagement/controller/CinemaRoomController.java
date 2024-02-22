package org.example.cinemamanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rooms")
public class CinemaRoomController {

    @GetMapping
    public ResponseEntity<?> getAllRoom() {
        return ResponseEntity.ok("All room");
    }

    @PostMapping
    public ResponseEntity<?> addRoom() {
        return ResponseEntity.ok("Room added");
    }

    @PutMapping
    public ResponseEntity<?> updateRoom() {
        return ResponseEntity.ok("Room updated");
    }

    @DeleteMapping
    public ResponseEntity<?> deleteRoom() {
        return ResponseEntity.ok("Room deleted");
    }
}
