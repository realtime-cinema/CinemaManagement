package org.example.cinemamanagement.controller;

import org.example.cinemamanagement.dto.CinemaDTO;
import org.example.cinemamanagement.dto.CinemaLayoutDTO;
import org.example.cinemamanagement.dto.CinemaManagerDTO;
import org.example.cinemamanagement.request.AddAndDeleteManagerRequest;
import org.example.cinemamanagement.service.CinemaManagerService;
import org.example.cinemamanagement.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cinemas")
public class CinemaController {

    CinemaService cinemaService;
    CinemaManagerService cinemaManagerService;

    @Autowired
    public CinemaController(CinemaService cinemaService, CinemaManagerService cinemaManagerService) {
        this.cinemaService = cinemaService;
        this.cinemaManagerService = cinemaManagerService;
    }

    @GetMapping
    public ResponseEntity<List<CinemaDTO>> getAllCinema() {
        return ResponseEntity.ok(cinemaService.getAllCinema());
    }


    @GetMapping("/{id}")
    public ResponseEntity<CinemaDTO> getCinema(@PathVariable(name = "id") UUID id) {
        CinemaDTO cinemaDTO = cinemaService.getCinema(id);

        return ResponseEntity.ok(cinemaDTO);
    }


    @PostMapping
    public ResponseEntity<CinemaDTO> addCinema(@RequestBody CinemaDTO cinemaDTO) {
        return ResponseEntity.ok(cinemaService.addCinema(cinemaDTO));
    }


    @PutMapping("/update-cinema")
    public ResponseEntity<?> updateCinema(@RequestBody CinemaDTO cinemaDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(cinemaService.updateCinema(cinemaDTO));
    }

    @DeleteMapping("/delete-cinema/{id}")
    public ResponseEntity<?> deleteCinema(@PathVariable UUID id) {
        cinemaService.deleteCinema(id);
        return ResponseEntity.status(HttpStatus.OK).body("Cinema deleted successfully");
    }


    @GetMapping("/get-all-manager-of-cinema/{cinemaID}")
    public ResponseEntity<?> getAllManager(@PathVariable UUID cinemaID) {

        return ResponseEntity.ok(cinemaManagerService.getAllCinemaManager(cinemaID));
    }

    @PostMapping("/add-manager")
    public ResponseEntity<?> addManager(@RequestBody AddAndDeleteManagerRequest addAndDeleteManagerRequest) {
        if (addAndDeleteManagerRequest.getEmailUser() == null || addAndDeleteManagerRequest.getIdCinema() == null) {
            return ResponseEntity.badRequest().body("Email or id cinema is null");
        }

        CinemaManagerDTO cinemaManagerDTO = cinemaManagerService.
                addCinemaManager(addAndDeleteManagerRequest.getEmailUser(),
                        addAndDeleteManagerRequest.getIdCinema()
                );

        if (cinemaManagerDTO == null) {
            return ResponseEntity.badRequest().body("User is already a manager of this cinema");
        }
        return ResponseEntity.ok(cinemaManagerDTO);
    }

    @DeleteMapping("/delete-manager")
    public ResponseEntity<?> deleteManagerOutOfCinema(@RequestBody AddAndDeleteManagerRequest addAndDeleteManagerRequest) {
        if (addAndDeleteManagerRequest.getEmailUser() == null || addAndDeleteManagerRequest.getIdCinema() == null) {
            return ResponseEntity.badRequest().body("Email or id cinema is null");
        }

        CinemaManagerDTO cinemaManagerDTO = cinemaManagerService.
                deleteCinemaManagerOutOfCinema(addAndDeleteManagerRequest.getEmailUser(),
                        addAndDeleteManagerRequest.getIdCinema()
                );

        if (cinemaManagerDTO == null) {
            return ResponseEntity.badRequest().body("User is not a manager of this cinema");
        }
        return ResponseEntity.ok(cinemaManagerDTO);
    }



}
