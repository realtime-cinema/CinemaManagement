package org.example.cinemamanagement.service;

import org.example.cinemamanagement.dto.PickSeatDTO;
import org.example.cinemamanagement.request.PickSeatRequest;

import java.util.List;
import java.util.UUID;

public interface PickSeatService {
    List<PickSeatDTO> getAllSeatsPickedOfPerform(UUID performID);

    List<PickSeatDTO> getAllPickSeatsByUser();

    PickSeatDTO getPickSeatById();

    public List<PickSeatDTO> addPickSeat(List<PickSeatRequest> pickSeatRequests, UUID performId);

    PickSeatDTO deletePickSeat();
}
