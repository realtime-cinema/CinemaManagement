package org.example.cinemamanagement.service;

import org.example.cinemamanagement.dto.CinemaDTO;
import org.example.cinemamanagement.dto.CinemaLayoutDTO;
import org.example.cinemamanagement.dto.CinemaRoomDTO;
import org.example.cinemamanagement.model.Cinema;
import org.example.cinemamanagement.model.CinemaLayout;
import org.example.cinemamanagement.repository.CinemaLayoutRepository;
import org.example.cinemamanagement.repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CinemaServiceImpl implements CinemaService {
    CinemaRepository cinemaRepository;
    CinemaLayoutRepository cinemaLayoutRepository;

    @Autowired
    public CinemaServiceImpl(CinemaRepository cinemaRepository, CinemaLayoutRepository cinemaLayoutRepository) {
        this.cinemaRepository = cinemaRepository;
        this.cinemaLayoutRepository = cinemaLayoutRepository;
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
    public CinemaDTO getCinema(UUID id) {
        Cinema cinema = cinemaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cinema not found with id: " + id));

        return CinemaDTO.builder()
                .id(cinema.getId())
                .name(cinema.getName())
                .variant(cinema.getVariant())
                .build();
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

/*    @Override
    @Transactional
    public CinemaLayoutDTO addCinemaLayout(UUID idCinema, CinemaLayoutDTO cinemaLayoutDTO) {
        CinemaLayout cinemaLayout = CinemaLayout.builder()
                .xIndex(cinemaLayoutDTO.getXIndex())
                .yIndex(cinemaLayoutDTO.getYIndex())
                .build();
        Optional<Cinema> cinema = cinemaRepository.findById(idCinema);
        if (cinema.isEmpty() || cinemaLayoutRepository.findByXIndexAndYIndex(cinemaLayout.getXIndex(), cinemaLayout.getYIndex()).isPresent())
            return null;

        cinema.get().addCinemaLayout(cinemaLayout);
        return CinemaLayoutDTO.builder()
                .id(cinemaLayoutRepository.save(cinemaLayout).getId())
                .xIndex(cinemaLayout.getXIndex())
                .yIndex(cinemaLayout.getYIndex())
                .build();
    }

    @Override
    public List<CinemaLayoutDTO> getAllCinemaLayout() {
        return cinemaLayoutRepository.findAll()
                .stream()
                .map(cinemaLayout ->
                        CinemaLayoutDTO.builder()
                                .id(cinemaLayout.getId())
                                .xIndex(cinemaLayout.getXIndex())
                                .yIndex(cinemaLayout.getYIndex())
                                .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCinemaLayout(UUID id) {
        cinemaLayoutRepository.deleteById(id);
    }

    @Override
    public CinemaLayoutDTO updateCinemaLayout(CinemaLayoutDTO cinemaLayoutDTO) {
        Optional<CinemaLayout> optionalCinemaLayout = cinemaLayoutRepository.findById(cinemaLayoutDTO.getId());
        if (optionalCinemaLayout.isPresent()) {
            CinemaLayout cinemaLayout = optionalCinemaLayout.get();
            cinemaLayout.setXIndex(cinemaLayoutDTO.getXIndex());
            cinemaLayout.setYIndex(cinemaLayoutDTO.getYIndex());
            return CinemaLayoutDTO.builder()
                    .id(cinemaLayoutRepository.save(cinemaLayout).getId())
                    .xIndex(cinemaLayout.getXIndex())
                    .yIndex(cinemaLayout.getYIndex())
                    .build();
        } else {
            throw new RuntimeException("Not found CinemaLayoutService with ID: " + cinemaLayoutDTO.getId());
        }

    }

    @Override
    public CinemaLayoutDTO getCinemaLayout(UUID id) {
        Optional<CinemaLayout> optionalCinemaLayout = cinemaLayoutRepository.findById(id);
        if (optionalCinemaLayout.isPresent()) {
            CinemaLayout cinemaLayout = optionalCinemaLayout.get();
            return CinemaLayoutDTO.builder()
                    .id(cinemaLayout.getId())
                    .xIndex(cinemaLayout.getXIndex())
                    .yIndex(cinemaLayout.getYIndex())
                    .build();
        } else {
            throw new RuntimeException("Not found CinemaLayoutService with ID: " + id);
        }

    }

    @Override
    public List<CinemaLayoutDTO> getCinemaLayoutByCinemaId(UUID id) {
        Optional<Cinema> optionalCinema = cinemaRepository.findById(id);
        if (optionalCinema.isPresent()) {
            Cinema cinema = optionalCinema.get();
            return cinema.getCinemaLayouts().stream()
                    .map(cinemaLayout ->
                            CinemaLayoutDTO.builder()
                                    .id(cinemaLayout.getId())
                                    .xIndex(cinemaLayout.getXIndex())
                                    .yIndex(cinemaLayout.getYIndex())
                                    .build()
                    )
                    .collect(Collectors.toList());
        } else {
            throw new RuntimeException("Not found CinemaID with ID: " + id);
        }

    }

    @Override
    public List<CinemaRoomDTO> getAllCinemaRoomByCinemaId(UUID id) {
        return null;
    }

    @Override
    public CinemaRoomDTO addCinemaRoom(CinemaRoomDTO cinemaRoomDTO) {
        return null;
    }*/
}

