package org.example.cinemamanagement.controller;

import org.example.cinemamanagement.dto.PickSeatDTO;
import org.example.cinemamanagement.request.PickSeatRequest;
import org.example.cinemamanagement.service.PickSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/pick-seat")
public class PickSeatController {

    PickSeatService pickSeatService;

    @Autowired
    public PickSeatController(PickSeatService pickSeatService) {
        this.pickSeatService = pickSeatService;
    }

    @GetMapping
    public String getPickSeat() {
        return "Pick Seat";
    }

    @GetMapping("/{performID}")
    public List<PickSeatDTO> getPickedSeatsByPerformID(@PathVariable UUID performID) {
        return pickSeatService.getAllSeatsPickedOfPerform(performID);
    }

    @PostMapping("/{performID}")
    public List<PickSeatDTO> addPickSeat(@PathVariable UUID performID,
                                         @RequestBody List<PickSeatRequest> pickSeatRequests) {
        System.out.println(pickSeatRequests);
        return pickSeatService.addPickSeat(pickSeatRequests, performID);
    }

    @DeleteMapping("/{id}")
    public String deletePickSeat() {
        return "Delete Pick Seat";
    }
}
