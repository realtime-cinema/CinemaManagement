package org.example.cinemamanagement.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.cinemamanagement.model.Cinema;
import org.example.cinemamanagement.model.User;
import org.example.cinemamanagement.repository.CinemaRepository;
import org.example.cinemamanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
    public void addCinemaManager(String emailUser, UUID idCinema) {
        User user = userRepository.findUserByEmail(emailUser).get();
        Cinema cinema = cinemaRepository.findById(idCinema).get();
//        user.addCinema(cinema);
//        userRepository.save(user);
        cinema.addUser(user);
        cinemaRepository.save(cinema);

        System.out.println(user);
        System.out.println(cinema);
//        user.getCinemaManagers().add(cinemaManager);
////        cinema.getCinemaManagers().add(cinemaManager);
////        userRepository.save(user);
//        System.out.println(user);
//        System.out.println(cinema);
//        System.out.println(cinemaManager);


    }
}