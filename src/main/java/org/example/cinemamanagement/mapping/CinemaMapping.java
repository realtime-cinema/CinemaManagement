package org.example.cinemamanagement.mapping;

import org.example.cinemamanagement.dto.CinemaDTO;
import org.example.cinemamanagement.dto.CinemaLayoutDTO;
import org.example.cinemamanagement.model.Cinema;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

public class CinemaMapping {
    public static CinemaDTO convert(Cinema cinema) {
        TypeMap<Cinema, CinemaDTO> typeMap = new ModelMapper().createTypeMap(Cinema.class, CinemaDTO.class);
        return typeMap.map(cinema);
    }
}
