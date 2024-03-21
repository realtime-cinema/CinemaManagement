package org.example.cinemamanagement.controller;

import org.example.cinemamanagement.payload.request.AddSeatPriceRequest;
import org.example.cinemamanagement.service.SeatPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/seat-price")
public class SeatPriceController {
    @Autowired
    private SeatPriceService seatPriceService;

    @PostMapping
    public ResponseEntity<?> addSeatPrice(@RequestBody AddSeatPriceRequest req) {
        return ResponseEntity.ok(seatPriceService.addSeatPrice(req));
    }
}
