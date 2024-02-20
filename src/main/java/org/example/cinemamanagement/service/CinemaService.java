package org.example.cinemamanagement.service;


import org.example.cinemamanagement.dto.CinemaDTO;
import org.example.cinemamanagement.dto.CinemaLayoutDTO;
import org.example.cinemamanagement.dto.CinemaRoomDTO;

import java.util.List;
import java.util.UUID;

public interface CinemaService {
    public List<CinemaDTO> getAllCinema();

    public CinemaDTO getCinema(UUID id);

    public CinemaDTO addCinema(CinemaDTO cinemaDTO);

    public void deleteCinema(UUID id);

    public CinemaDTO updateCinema(CinemaDTO cinemaDTO);

    public CinemaLayoutDTO addCinemaLayout(UUID idCinema, CinemaLayoutDTO cinemaLayoutDTO);

    public List<CinemaLayoutDTO> getAllCinemaLayout();

    public void deleteCinemaLayout(UUID id);

    public CinemaLayoutDTO updateCinemaLayout(CinemaLayoutDTO cinemaLayoutDTO);

    public CinemaLayoutDTO getCinemaLayout(UUID id);

    public List<CinemaLayoutDTO> getCinemaLayoutByCinemaId(UUID id);

    public List<CinemaRoomDTO> getAllCinemaRoomByCinemaId(UUID id);

    public CinemaRoomDTO addCinemaRoom(CinemaRoomDTO cinemaRoomDTO);
}
