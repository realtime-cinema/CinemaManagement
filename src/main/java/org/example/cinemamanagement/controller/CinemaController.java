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
@RequestMapping("/api/cinema")
public class CinemaController {

    CinemaService cinemaService;
    CinemaManagerService cinemaManagerService;

    @Autowired
    public CinemaController(CinemaService cinemaService, CinemaManagerService cinemaManagerService) {
        this.cinemaService = cinemaService;
        this.cinemaManagerService = cinemaManagerService;
    }

    @GetMapping("/get-all-cinema")
    public ResponseEntity<List<CinemaDTO>> getAllCinema() {
        return ResponseEntity.ok(cinemaService.getAllCinema());
    }


    @GetMapping("/get-cinema/{id}")
    public ResponseEntity<CinemaDTO> getCinema(@PathVariable UUID id) {
        CinemaDTO cinemaDTO = cinemaService.getCinema(id);
        if (cinemaDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(cinemaDTO);
    }


    @PostMapping("/add-cinema")
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
        if (cinemaID == null) {
            return ResponseEntity.badRequest().body("Id is null");
        }
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

    @GetMapping("/get-all-layout")
    public ResponseEntity<?> getAllLayout() {
        return ResponseEntity.ok("All layout");
    }

    @PostMapping("/add-layout/{idCinema}")
    public ResponseEntity<?> addLayout(@PathVariable UUID idCinema, @RequestBody CinemaLayoutDTO cinemaLayoutDTO) throws Exception {
        if (idCinema == null) {
            return ResponseEntity.badRequest().body("Id is null");
        }
        return ResponseEntity.ok(cinemaService.addCinemaLayout(idCinema, cinemaLayoutDTO));
    }

    @PutMapping("/update-layout")
    public ResponseEntity<?> updateLayout() {
        return ResponseEntity.ok("Layout updated");
    }

    @DeleteMapping("/delete-layout")
    public ResponseEntity<?> deleteLayout() {
        return ResponseEntity.ok("Layout deleted");
    }

    @GetMapping("/get-all-room")
    public ResponseEntity<?> getAllRoom() {
        return ResponseEntity.ok("All room");
    }

    @PostMapping("/add-room")
    public ResponseEntity<?> addRoom() {
        return ResponseEntity.ok("Room added");
    }

    @PutMapping("/update-room")
    public ResponseEntity<?> updateRoom() throws Exception {
        return ResponseEntity.ok("Room updated");
    }

    @DeleteMapping("/delete-room")
    public ResponseEntity<?> deleteRoom() {
        return ResponseEntity.ok("Room deleted");
    }
}
