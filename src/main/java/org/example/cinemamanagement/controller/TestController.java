package org.example.cinemamanagement.controller;

import org.example.cinemamanagement.dto.CinemaDTO;
import org.example.cinemamanagement.dto.CinemaManagerDTO;
import org.example.cinemamanagement.model.Cinema;
import org.example.cinemamanagement.model.User;
import org.example.cinemamanagement.repository.CinemaRepository;
import org.example.cinemamanagement.repository.UserRepository;
import org.example.cinemamanagement.request.AddManagerRequest;
import org.example.cinemamanagement.service.CinemaManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CinemaRepository cinemaRepository;


    @Autowired
    CinemaManagerService cinemaManagerService;

    @GetMapping("/getalluser")
    public ResponseEntity<?> GetAllUser() {
        List<User> tempList = userRepository.findAll().stream().toList();
        System.out.println(tempList);
        return ResponseEntity.ok(tempList);
    }

    @PostMapping("/addcinema")
    public void addCinema(@RequestBody CinemaDTO cinemaDTO) {
        Cinema cinema = Cinema.builder().
                variant(cinemaDTO.getVariant()).
                name(cinemaDTO.getName()).
                build();
        cinemaRepository.save(cinema);
    }

    @PostMapping("/addManager")
    public void addManager(@RequestBody AddManagerRequest addManagerRequest) {
        System.out.println(addManagerRequest);
        cinemaManagerService.addCinemaManager(
                addManagerRequest.getEmailUser(),
                addManagerRequest.getIdCinema()
        );

    }

    @GetMapping("/logcinema")
    public ResponseEntity<?> logCinema() {
//
//        List<CinemaDTO> cinemaDTOS = new ArrayList<>();
//        List<Cinema> cinemas = cinemaRepository.findAll();
//
//        cinemas.forEach(cinema -> {
//            cinemaDTOS.add(CinemaDTO.builder()
////                    .cinemaLayouts(cinema.getCinemaLayouts())
////                    .cinemaRooms(cinema.getCinemaRooms())
//                    .name(cinema.getName())
//                    .cinemaManagers(cinema.getCinemaManagers())
//                    .variant(cinema.getVariant())
//                    .build());
//        });
//        for (CinemaDTO cinemaDTO : cinemaDTOS) {
//            System.out.println(cinemaDTOS);
//        }
//
//        return ResponseEntity.ok(cinemaDTOS);
        List<Cinema> cinemas = cinemaRepository.findAll();
        List<CinemaManagerDTO> cinemaManagerDTOS = new ArrayList<>();
        cinemas.forEach(cinema -> {
            cinemaManagerDTOS.add(CinemaManagerDTO.builder()
                    .cinema(cinema)
                    .user(cinema.getCinemaManagers().get(0))
                    .build());
        });

        return ResponseEntity.ok(cinemaManagerDTOS);
    }
}
