package org.example.cinemamanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CinemaRoomDTO {
    private UUID id;
    private CinemaDTO cinemaDTO;
    private CinemaLayoutDTO cinemaLayoutDTO;
    private String name;
}


