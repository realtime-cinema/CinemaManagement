package org.example.cinemamanagement.service;

import org.example.cinemamanagement.dto.CinemaDTO;
import org.example.cinemamanagement.dto.CinemaLayoutDTO;
import org.example.cinemamanagement.dto.CinemaRoomDTO;
import org.example.cinemamanagement.model.Cinema;
import org.example.cinemamanagement.model.CinemaLayout;
import org.example.cinemamanagement.repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CinemaServiceImpl implements CinemaService {
    CinemaRepository cinemaRepository;

    @Autowired
    public CinemaServiceImpl(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    @Override
    public List<CinemaDTO> getAllCinema() {
        return cinemaRepository.findAll()
                .stream()
                .map(cinema ->
                        CinemaDTO.builder()
                                .id(cinema.getId())
                                .name(cinema.getName())
                                .variant(cinema.getVariant())
                                .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    public CinemaDTO addCinema(CinemaDTO cinemaDTO) {
        Cinema cinema = Cinema.builder()
                .name(cinemaDTO.getName())
                .variant(cinemaDTO.getVariant())
                .build();
        return CinemaDTO.builder()
                .id(cinemaRepository.save(cinema).getId())
                .name(cinema.getName())
                .variant(cinema.getVariant())
                .build();
    }

    @Override
    public void deleteCinema(UUID id) {
        cinemaRepository.deleteById(id);
    }

    @Override
    public CinemaDTO updateCinema(CinemaDTO cinemaDTO) {
        Cinema cinema = cinemaRepository.findById(cinemaDTO.getId()).get();
        cinema.setName(cinemaDTO.getName());
        cinema.setVariant(cinemaDTO.getVariant());
        return CinemaDTO.builder()
                .id(cinemaRepository.save(cinema).getId())
                .name(cinema.getName())
                .variant(cinema.getVariant())
                .build();
    }

    @Override
    public CinemaLayout addCinemaLayout(CinemaLayoutDTO cinemaLayoutDTO) {
        return null;
    }

    @Override
    public List<CinemaLayoutDTO> getAllCinemaLayout() {
        return null;
    }

    @Override
    public void deleteCinemaLayout(UUID id) {

    }

    @Override
    public CinemaLayoutDTO updateCinemaLayout(CinemaLayoutDTO cinemaLayoutDTO) {
        return null;
    }

    @Override
    public CinemaLayoutDTO getCinemaLayout(UUID id) {
        return null;
    }

    @Override
    public List<CinemaLayoutDTO> getCinemaLayoutByCinemaId(UUID id) {
        return null;
    }

    @Override
    public List<CinemaRoomDTO> getAllCinemaRoomByCinemaId(UUID id) {
        return null;
    }

    @Override
    public CinemaRoomDTO addCinemaRoom(CinemaRoomDTO cinemaRoomDTO) {
        return null;
    }
}

