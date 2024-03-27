package org.example.cinemamanagement.service;

import org.example.cinemamanagement.payload.request.AddPickSeatRequest;

import java.util.List;
import java.util.UUID;

public interface SeatPaymentService {
    public String addListSeatOfPayment(UUID paymentId, List<AddPickSeatRequest> listSeat);
}
