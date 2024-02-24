package org.example.cinemamanagement.service.imp;

import org.example.cinemamanagement.dto.CinemaDTO;
import org.example.cinemamanagement.dto.CinemaLayoutDTO;
import org.example.cinemamanagement.dto.CinemaRoomDTO;
import org.example.cinemamanagement.model.Cinema;
import org.example.cinemamanagement.model.CinemaLayout;
import org.example.cinemamanagement.model.CinemaRoom;
import org.example.cinemamanagement.repository.CinemaLayoutRepository;
import org.example.cinemamanagement.repository.CinemaRepository;
import org.example.cinemamanagement.repository.CinemaRoomRepository;
import org.example.cinemamanagement.request.AddCinemaRoomRequest;
import org.example.cinemamanagement.service.CinemaRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CinemaRoomServiceImpl implements CinemaRoomService {

    public CinemaLayoutRepository cinemaLayoutRepository;
    public CinemaRepository cinemaRepository;

    public CinemaRoomRepository cinemaRoomRepository;

    @Autowired
    public CinemaRoomServiceImpl(CinemaLayoutRepository cinemaLayoutRepository, CinemaRepository cinemaRepository
            , CinemaRoomRepository cinemaRoomRepository) {
        this.cinemaLayoutRepository = cinemaLayoutRepository;
        this.cinemaRepository = cinemaRepository;
        this.cinemaRoomRepository = cinemaRoomRepository;
    }

    @Override
    public List<CinemaRoomDTO> getAllCinemaRooms() {
        return null;
    }


    @Override
    public CinemaRoomDTO getCinemaRoomById(UUID id) {
        return null;
    }

    @Override
    @Transactional
    public CinemaRoomDTO addCinemaRoom(AddCinemaRoomRequest addCinemaRoomRequest) {
        Cinema cinema = cinemaRepository.findById(addCinemaRoomRequest.getCinemaId())
                .orElseThrow(() -> new RuntimeException("Cinema not found"));

        CinemaLayout layout = cinemaLayoutRepository.findById(addCinemaRoomRequest.getCinemaLayoutId())
                .orElseThrow(() -> new RuntimeException("Cinema layout not found"));

        Optional<CinemaRoom> cinemaRoom = cinemaRoomRepository.findByNameAndCinemaId(addCinemaRoomRequest.getName(),
                addCinemaRoomRequest.getCinemaId());

        if (cinemaRoom.isEmpty()) {
            if (!cinema.getCinemaLayouts().contains(layout)) {
                cinema.addCinemaLayout(layout);
            }
            return CinemaRoomDTO.builder()
                    .id(cinemaRoomRepository.save(CinemaRoom.builder()
                            .cinema(cinema)
                            .cinemaLayout(layout)
                            .name(addCinemaRoomRequest.getName())
                            .build()).getId())
                    .cinemaDTO(CinemaDTO.builder()
                            .id(cinema.getId())
                            .name(cinema.getName())
                            .variant(cinema.getVariant())
                            .build())
                    .cinemaLayoutDTO(CinemaLayoutDTO.builder()
                            .id(layout.getId())
                            .xIndex(layout.getXIndex())
                            .yIndex(layout.getYIndex())
                            .build())
                    .name(addCinemaRoomRequest.getName())
                    .build();
        }

        throw new RuntimeException("Cinema room already exists");
    }

    @Override
    public CinemaRoomDTO updateCinemaRoom(CinemaRoomDTO cinemaRoomDTO) {
        return null;
    }

    @Override
    public void deleteCinemaRoom(UUID id) {

    }
}
