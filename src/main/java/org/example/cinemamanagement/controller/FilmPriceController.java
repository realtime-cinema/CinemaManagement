package org.example.cinemamanagement.controller;

import org.example.cinemamanagement.payload.request.AddFilmPriceRequest;
import org.example.cinemamanagement.payload.response.DataResponse;
import org.example.cinemamanagement.service.FilmPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/film-price")
public class FilmPriceController {

    @Autowired
    private FilmPriceService filmPriceService;

    @PostMapping
    public ResponseEntity<?> addFilmPrice(@RequestBody AddFilmPriceRequest req) {

        filmPriceService.addFilmPrice(req);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Add film price successfully");
        dataResponse.setStatus(HttpStatus.OK);

        return ResponseEntity.ok(dataResponse);
    }
}
