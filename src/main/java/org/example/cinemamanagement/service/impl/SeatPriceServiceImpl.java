package org.example.cinemamanagement.service.impl;

import org.example.cinemamanagement.model.Perform;
import org.example.cinemamanagement.model.SeatPrice;
import org.example.cinemamanagement.payload.request.AddSeatPriceRequest;
import org.example.cinemamanagement.repository.PerformRepository;
import org.example.cinemamanagement.repository.SeatPriceRepository;
import org.example.cinemamanagement.service.SeatPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatPriceServiceImpl implements SeatPriceService {
    @Autowired
    private PerformRepository performRepository;

    @Autowired
    private SeatPriceRepository seatPriceRepository;

    @Override
    public String addSeatPrice(AddSeatPriceRequest req) {
        Perform perform = performRepository.findById(req.getPerformId())
                .orElseThrow(() -> new RuntimeException("Perform not found"));

        if (req.getX() == null || req.getY() == null)
            throw new RuntimeException("X or Y not allow NULL");

        if (seatPriceRepository.findByXAndY(req.getX(), req.getY()) != null)
            throw new RuntimeException("X and Y already exist");

        if (req.getPrice() == null)
            throw new RuntimeException("Price not allow NULL");

        SeatPrice seatPrice = new SeatPrice();
        seatPrice.setPerform(perform);
        seatPrice.setX(req.getX());
        seatPrice.setY(req.getY());
        seatPrice.setPrice(req.getPrice());
        seatPriceRepository.save(seatPrice);

        return "Successfully";
    }
}
