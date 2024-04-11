package org.example.cinemamanagement.controller;

import org.example.cinemamanagement.dto.FilmDTO;
import org.example.cinemamanagement.payload.request.AddFilmRequest;
import org.example.cinemamanagement.payload.response.DataResponse;
import org.example.cinemamanagement.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/films")
public class FilmController {

    FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public ResponseEntity<?> getFilms() {

        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Get all films successfully");
        dataResponse.setData(filmService.getAllFilms());

        return ResponseEntity.ok(dataResponse);
    }

    @PostMapping
    public ResponseEntity<?> addFilm(@RequestBody AddFilmRequest addFilmRequest) {

        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Add film successfully");
        dataResponse.setData(filmService.addFilm(addFilmRequest));

        return ResponseEntity.ok(dataResponse);
    }
}
