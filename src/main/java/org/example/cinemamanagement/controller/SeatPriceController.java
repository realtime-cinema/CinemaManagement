package org.example.cinemamanagement.controller;

import org.example.cinemamanagement.payload.request.AddSeatPriceRequest;
import org.example.cinemamanagement.service.SeatPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/seat-price")
public class SeatPriceController {
    @Autowired
    private SeatPriceService seatPriceService;

    @PostMapping
    public ResponseEntity<?> addSeatPrice(@RequestBody AddSeatPriceRequest req) {
        return ResponseEntity.ok(seatPriceService.addSeatPrice(req));
    }

    @GetMapping("/{performId}")
    public ResponseEntity<?> getAllSeatPrice(@PathVariable UUID performId) {
        return ResponseEntity.ok(seatPriceService.getAllSeatPrice(performId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSeatPrice(@PathVariable UUID id) {
        return ResponseEntity.ok(seatPriceService.deleteSeatPrice(id));
    }
}
