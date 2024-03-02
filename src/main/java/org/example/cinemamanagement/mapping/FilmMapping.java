package org.example.cinemamanagement.mapping;

import org.example.cinemamanagement.dto.FilmDTO;
import org.example.cinemamanagement.model.Film;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

public class FilmMapping {
    public static FilmDTO toDTO(Film film) {
        TypeMap<Film, FilmDTO> typeMap = new ModelMapper().createTypeMap(Film.class, FilmDTO.class);
        return typeMap.map(film);
    }
}
