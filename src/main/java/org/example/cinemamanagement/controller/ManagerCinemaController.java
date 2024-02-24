package org.example.cinemamanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/managers")
public class ManagerCinemaController {

    /*@PostMapping("/add-manager")
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
    }*/
    @GetMapping("/all")
    public ResponseEntity<?> getAllManager() {
        System.out.println("Hello world");
        return ResponseEntity.ok("All manager");
    }
}
