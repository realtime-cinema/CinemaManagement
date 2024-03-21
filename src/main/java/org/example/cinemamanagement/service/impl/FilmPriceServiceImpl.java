package org.example.cinemamanagement.service.impl;

import org.example.cinemamanagement.model.Film;
import org.example.cinemamanagement.model.FilmPrice;
import org.example.cinemamanagement.payload.request.AddFilmPriceRequest;
import org.example.cinemamanagement.repository.FilmPriceRepository;
import org.example.cinemamanagement.repository.FilmRepository;
import org.example.cinemamanagement.service.FilmPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmPriceServiceImpl implements FilmPriceService {

    @Autowired
    private FilmPriceRepository filmPriceRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Override
    public String addFilmPrice(AddFilmPriceRequest req) {
        Film film = filmRepository.findById(req.getFilmId())
                .orElseThrow(() -> new RuntimeException("Film not found"));
        if (req.getPrice() == null)
            throw new RuntimeException("Type not allow NULL");
        if (req.getPrice() == null)
            throw new RuntimeException("Price not allow NULL");

        FilmPrice filmPrice = new FilmPrice();
        filmPrice.setFilm(film);
        filmPrice.setType(req.getType());
        filmPrice.setPrice(req.getPrice());
        filmPriceRepository.save(filmPrice);

        return "Successfully";
    }
}
