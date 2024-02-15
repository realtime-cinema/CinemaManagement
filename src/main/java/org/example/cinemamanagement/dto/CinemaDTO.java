package org.example.cinemamanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.cinemamanagement.model.CinemaLayout;
import org.example.cinemamanagement.model.CinemaRoom;
import org.example.cinemamanagement.model.User;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CinemaDTO {
    private List<User> cinemaManagers;
    private String variant;
    private String name;
    private List<CinemaLayout> cinemaLayouts;
    private List<CinemaRoom> cinemaRooms;
}

