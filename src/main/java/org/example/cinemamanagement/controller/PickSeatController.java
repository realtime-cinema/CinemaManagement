package org.example.cinemamanagement.controller;

import org.example.cinemamanagement.payload.request.DeletePickSeatRequest;
import org.example.cinemamanagement.payload.request.PickSeatRequest;
import org.example.cinemamanagement.payload.response.DataResponse;
import org.example.cinemamanagement.service.PickSeatService;
import org.example.cinemamanagement.service.SocketIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/pick-seat")
public class PickSeatController {

    PickSeatService pickSeatService;
    SocketIOService socketIOService;

    @Autowired
    public PickSeatController(PickSeatService pickSeatService, SocketIOService socketIOService) {
        this.pickSeatService = pickSeatService;
        this.socketIOService = socketIOService;
    }

    @GetMapping
    public String getPickSeat() {
        return "Pick Seat";
    }

    @GetMapping("/{performID}")
    public ResponseEntity<?> getPickedSeatsByPerformID(@PathVariable UUID performID) {

        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Get all picked seats successfully");
        dataResponse.setData(pickSeatService.getAllSeatsPickedOfPerform(performID));
        return ResponseEntity.ok(dataResponse);
    }

    @PostMapping("/{performID}")
    public ResponseEntity<?> addPickSeats(@PathVariable UUID performID,
                                          @RequestBody List<PickSeatRequest> pickSeatRequests) {

        Object data = pickSeatService.addPickSeat(pickSeatRequests, performID);

        socketIOService.emit("seat-add", data);
        return ResponseEntity.ok(DataResponse.builder()
                .data(data)
                .message("Add pick seat successfully")
                .build());
    }

    @DeleteMapping("/{performID}")
    public ResponseEntity<?> deletePickSeat(@RequestBody List<DeletePickSeatRequest> DeletePickSeatRequests, @PathVariable UUID performID) {
        Object data = pickSeatService.deletePickSeat(DeletePickSeatRequests, performID);
        socketIOService.emit("seat-remove", data);
        return ResponseEntity.ok(DataResponse.builder()
                .data(data)
                .message("Delete pick seat successfully")
                .build());
    }

}
