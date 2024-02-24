package org.example.cinemamanagement.service.imp;

import org.example.cinemamanagement.dto.CinemaLayoutDTO;
import org.example.cinemamanagement.model.CinemaLayout;
import org.example.cinemamanagement.repository.CinemaLayoutRepository;
import org.example.cinemamanagement.service.CinemaLayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CinemaLayoutServiceImpl implements CinemaLayoutService {

    @Autowired
    private CinemaLayoutRepository cinemaLayoutRepository;

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
    public CinemaLayoutDTO getCinemaLayout(UUID id) {
        CinemaLayout layout = cinemaLayoutRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Cinema layout not found with id: " + id));
        return CinemaLayoutDTO.builder()
                .id(layout.getId())
                .xIndex(layout.getXIndex())
                .yIndex(layout.getYIndex())
                .build();
    }

    @Override
    @Transactional
    public CinemaLayoutDTO addCinemaLayout(CinemaLayoutDTO cinemaLayoutDTO) {
        cinemaLayoutRepository.findByXIndexAndYIndex(cinemaLayoutDTO.getXIndex()
                        , cinemaLayoutDTO.getYIndex())
                .ifPresent(layout -> {
                    throw new RuntimeException("Cinema layout already exists with xIndex: " + layout.getXIndex() + " and yIndex: " + layout.getYIndex());
                });

        return CinemaLayoutDTO.builder()
                .id(cinemaLayoutRepository.save(CinemaLayout.builder()
                        .xIndex(cinemaLayoutDTO.getXIndex())
                        .yIndex(cinemaLayoutDTO.getYIndex())
                        .build()).getId())
                .xIndex(cinemaLayoutDTO.getXIndex())
                .yIndex(cinemaLayoutDTO.getYIndex())
                .build();
    }


    @Override
    @Transactional
    public CinemaLayoutDTO updateCinemaLayout(UUID idLayout, CinemaLayoutDTO cinemaLayoutDTO) {
        CinemaLayout layout = cinemaLayoutRepository
                .findById(idLayout)
                .orElseThrow(() ->
                        new RuntimeException("Cinema layout not found with id: " + idLayout));
        if (cinemaLayoutDTO.getXIndex() != null
                && !cinemaLayoutDTO.getXIndex().equals(layout.getXIndex()))
            layout.setXIndex(cinemaLayoutDTO.getXIndex());

        if (cinemaLayoutDTO.getYIndex() != null
                && !cinemaLayoutDTO.getYIndex().equals(layout.getYIndex()))
            layout.setYIndex(cinemaLayoutDTO.getYIndex());

        return CinemaLayoutDTO.builder()
                .id(idLayout)
                .xIndex(layout.getXIndex())
                .yIndex(layout.getYIndex())
                .build();
    }

    @Override
    @Transactional
    public void deleteCinemaLayout(UUID id) {
        CinemaLayout layout = cinemaLayoutRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Cinema layout not found with id: " + id));

        cinemaLayoutRepository.deleteById(id);
    }
}
