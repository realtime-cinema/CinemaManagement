package org.example.cinemamanagement.controller;

import org.example.cinemamanagement.dto.FilmDTO;
import org.example.cinemamanagement.payload.request.AddFilmRequest;
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
    public ResponseEntity<List<FilmDTO>> getFilms() {
        return ResponseEntity.ok(filmService.getAllFilms());
    }

    @PostMapping
    public ResponseEntity<FilmDTO> addFilm(@RequestBody AddFilmRequest addFilmRequest) {
        System.out.println(addFilmRequest);
        return ResponseEntity.ok(filmService.addFilm(addFilmRequest));
    }
}
