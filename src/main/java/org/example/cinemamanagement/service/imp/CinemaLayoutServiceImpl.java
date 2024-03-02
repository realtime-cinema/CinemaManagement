package org.example.cinemamanagement.service.imp;

import org.example.cinemamanagement.dto.CinemaLayoutDTO;
import org.example.cinemamanagement.mapping.CinemaLayoutMapping;
import org.example.cinemamanagement.model.CinemaLayout;
import org.example.cinemamanagement.repository.CinemaLayoutRepository;
import org.example.cinemamanagement.request.AddCinemaLayoutRequest;
import org.example.cinemamanagement.service.CinemaLayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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
                .map(CinemaLayoutMapping::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CinemaLayoutDTO getCinemaLayout(UUID id) {
        CinemaLayout layout = cinemaLayoutRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Cinema layout not found with id: " + id));
        return CinemaLayoutMapping.toDTO(layout);
    }

    @Override
    @Transactional
    public CinemaLayoutDTO addCinemaLayout(AddCinemaLayoutRequest cinemaLayoutRequest) {
        Optional<CinemaLayout> layout = cinemaLayoutRepository.findByXIndexAndYIndex(cinemaLayoutRequest.getXIndex()
                                                                                    ,cinemaLayoutRequest.getYIndex());
        if (layout.isPresent())
            throw new RuntimeException("Cinema layout already exists with xIndex: " + layout.get().getXIndex() + " and yIndex: " + layout.get().getYIndex());
        else {
            layout.get().setXIndex(cinemaLayoutRequest.getXIndex());
            layout.get().setYIndex(cinemaLayoutRequest.getYIndex());
            cinemaLayoutRepository.save(layout.get());
        }

        return CinemaLayoutMapping.toDTO(layout.get());
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
        cinemaLayoutRepository.save(layout);

        return CinemaLayoutMapping.toDTO(layout);
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
