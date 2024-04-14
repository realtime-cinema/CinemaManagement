package org.example.cinemamanagement.controller;

import org.example.cinemamanagement.payload.request.PickSeatRequest;
import org.example.cinemamanagement.payload.response.DataResponse;
import org.example.cinemamanagement.service.PickSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getPickedSeatsByPerformID(@PathVariable UUID performID) {

        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Get all picked seats successfully");
        dataResponse.setStatus(HttpStatus.OK);
        dataResponse.setData(pickSeatService.getAllSeatsPickedOfPerform(performID));

        return ResponseEntity.ok(dataResponse);
    }

    @PostMapping("/{performID}")
    public ResponseEntity<?> addPickSeat(@PathVariable UUID performID,
                                         @RequestBody List<PickSeatRequest> pickSeatRequests) {

        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Add pick seat successfully");
        dataResponse.setStatus(HttpStatus.OK);
        dataResponse.setData(pickSeatService.addPickSeat(pickSeatRequests, performID));

        return ResponseEntity.ok(dataResponse);
    }

    @DeleteMapping("/{id}")
    public String deletePickSeat() {
        return "Delete Pick Seat";
    }
}
