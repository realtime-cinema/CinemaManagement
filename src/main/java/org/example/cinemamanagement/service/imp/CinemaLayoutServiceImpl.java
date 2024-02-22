package org.example.cinemamanagement.service.imp;

import org.example.cinemamanagement.dto.CinemaLayoutDTO;
import org.example.cinemamanagement.repository.CinemaLayoutRepository;
import org.example.cinemamanagement.service.CinemaLayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public CinemaLayoutDTO updateCinemaLayout(CinemaLayoutDTO cinemaLayoutDTO) {
        return null;
    }

    @Override
    public void deleteCinemaLayout(UUID id) {

    }
}
