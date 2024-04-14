package org.example.cinemamanagement.controller;

import org.example.cinemamanagement.dto.CinemaDTO;
import org.example.cinemamanagement.payload.request.AddCinemaRequest;
import org.example.cinemamanagement.payload.response.DataResponse;
import org.example.cinemamanagement.service.CinemaManagerService;
import org.example.cinemamanagement.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cinemas")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private CinemaManagerService cinemaManagerService;

    /**
     *
     *                   CRUD basic
     */
    @GetMapping
    public ResponseEntity<?> getAllCinema() {

        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Get all cinemas successfully");
        dataResponse.setStatus(HttpStatus.OK);
        dataResponse.setData(cinemaService.getAllCinema());

        return ResponseEntity.ok(dataResponse);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getCinema(@PathVariable(name = "id") UUID id) {

        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Get cinema successfully");
        dataResponse.setStatus(HttpStatus.OK);
        dataResponse.setData(cinemaService.getCinema(id));

        return ResponseEntity.ok(dataResponse);
    }


    @PostMapping
    public ResponseEntity<?> addCinema(@RequestBody AddCinemaRequest addCinemaRequest) {

        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Add cinema successfully");
        dataResponse.setStatus(HttpStatus.OK);
        dataResponse.setData(cinemaService.addCinema(addCinemaRequest));

        return ResponseEntity.ok(dataResponse);
    }


    @PutMapping
    public ResponseEntity<?> updateCinema(@RequestBody CinemaDTO cinemaDTO) {

        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Update cinema successfully");
        dataResponse.setStatus(HttpStatus.OK);
        dataResponse.setData(cinemaService.updateCinema(cinemaDTO));

        return ResponseEntity.ok(dataResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCinema(@PathVariable(value = "id") UUID id) {

        cinemaService.deleteCinema(id);

        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Cinema deleted successfully");
        dataResponse.setStatus(HttpStatus.OK);
        
        return ResponseEntity.ok(dataResponse);
    }


    /**
     *
     *                       Another
     */
    @GetMapping("/{id}/managers")
    public ResponseEntity<?> getAllManagerFromCinema(@PathVariable(name = "id") UUID id) {

        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Get all manager from cinema successfully");
        dataResponse.setStatus(HttpStatus.OK);
        dataResponse.setData(cinemaManagerService.getAllCinemaManagerFromCinema(id));

        return ResponseEntity.ok(dataResponse);
    }
}
