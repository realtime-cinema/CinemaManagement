package org.example.cinemamanagement.service;


import org.example.cinemamanagement.dto.CinemaManagerDTO;

import java.util.List;
import java.util.UUID;

public interface CinemaManagerService {
    public CinemaManagerDTO addCinemaManager(String emailUser, UUID idCinema);

    //    create some feature about cinema manager
    public CinemaManagerDTO deleteCinemaManagerOutOfCinema(String emailUser, UUID idCinema);

    public void updateCinemaManager(String emailUser, UUID idCinema);

    public void getCinemaManager(String emailUser, UUID idCinema);

    public List<CinemaManagerDTO> getAllCinemaManager(UUID idCinema);


}

