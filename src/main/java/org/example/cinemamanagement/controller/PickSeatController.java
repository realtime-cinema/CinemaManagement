package org.example.cinemamanagement.controller;

import org.example.cinemamanagement.dto.PickSeatDTO;
import org.example.cinemamanagement.payload.request.DeletePickSeatRequest;
import org.example.cinemamanagement.payload.request.PickSeatRequest;
import org.example.cinemamanagement.payload.response.DataResponse;
import org.example.cinemamanagement.payload.response.SocketResponse;
import org.example.cinemamanagement.service.PickSeatService;
import org.example.cinemamanagement.service.SocketIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
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
    public ResponseEntity<?> addPickSeat(@PathVariable UUID performID,
                                         @RequestBody List<PickSeatRequest> pickSeatRequests) {

        List<PickSeatDTO> data = pickSeatService.addPickSeat(pickSeatRequests, performID);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Add pick seat successfully");

        dataResponse.setData(data);

        List<SocketResponse> socketResponses = data.stream().map(pickSeatDTO -> {
            return SocketResponse.builder()
                    .x(pickSeatDTO.getX())
                    .y(pickSeatDTO.getY())
//                    .roomID(pickSeatDTO.getPerformDTO().getCinemaRoomDTO().getId())
                    .performID(pickSeatDTO.getPerformDTO().getId())
                    .build();
        }).toList();

        socketIOService.emit("pick-seat", socketResponses);
        return ResponseEntity.ok(dataResponse);
    }

    @DeleteMapping("/{performID}")
    public ResponseEntity<?> deletePickSeat(@RequestBody List<DeletePickSeatRequest> DeletePickSeatRequests, @PathVariable UUID performID) {
        Object data = pickSeatService.deletePickSeat(DeletePickSeatRequests, performID);
        // this emit for sending to client the existed pick seat
        socketIOService.emit("pick-seat", data);
        return ResponseEntity.ok(DataResponse.builder()
                .data(data)
                .message("Delete pick seat successfully")
                .build());
    }
}
