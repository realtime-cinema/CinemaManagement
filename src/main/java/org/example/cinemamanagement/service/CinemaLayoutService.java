package org.example.cinemamanagement.service;

import org.example.cinemamanagement.dto.CinemaLayoutDTO;

import java.util.List;
import java.util.UUID;

public interface CinemaLayoutService {
    public List<CinemaLayoutDTO> getAllCinemaLayout();

    public CinemaLayoutDTO getCinemaLayout(UUID id);

    public CinemaLayoutDTO updateCinemaLayout(CinemaLayoutDTO cinemaLayoutDTO);

    public void deleteCinemaLayout(UUID id);

}
