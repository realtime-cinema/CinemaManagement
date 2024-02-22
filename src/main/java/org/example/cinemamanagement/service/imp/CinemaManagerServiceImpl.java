package org.example.cinemamanagement.service.imp;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.cinemamanagement.common.Role;
import org.example.cinemamanagement.dto.CinemaManagerDTO;
import org.example.cinemamanagement.model.Cinema;
import org.example.cinemamanagement.model.User;
import org.example.cinemamanagement.repository.CinemaRepository;
import org.example.cinemamanagement.repository.UserRepository;
import org.example.cinemamanagement.service.CinemaManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CinemaManagerServiceImpl implements CinemaManagerService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CinemaRepository cinemaRepository;

    @Override
    @Transactional
    public CinemaManagerDTO addCinemaManager(String emailUser, UUID idCinema) {
        User user = userRepository.findUserByEmail(emailUser).get();
        Cinema cinema = cinemaRepository.findById(idCinema).get();
        if (cinema.getCinemaManagers().contains(user)) {
            return null;
        }
        user.setRole(Role.MANAGER_ADMIN);
        cinema.addUser(user);
        return CinemaManagerDTO.builder().cinemas(user.getCinemas()).user(user).build();

    }

    @Override
    public void updateCinemaManager(String emailUser, UUID idCinema) {

    }

    @Override
    public void getCinemaManager(String emailUser, UUID idCinema) {

    }

    @Override
    public List<CinemaManagerDTO> getAllCinemaManagerFromCinema(UUID idCinema) {
        return cinemaRepository.findById(idCinema).get()
                .getCinemaManagers()
                .stream().map(
                        user -> {
                            return CinemaManagerDTO.builder().user(user)
                                    .cinemas(user.getCinemas())
                                    .build();
                        }
                ).collect(Collectors.toList());
    }
}

