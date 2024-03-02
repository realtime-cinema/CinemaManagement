package org.example.cinemamanagement.mapping;

import org.example.cinemamanagement.dto.CinemaLayoutDTO;
import org.example.cinemamanagement.model.CinemaLayout;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

public class CinemaLayoutMapping {
    public static CinemaLayoutDTO toDTO(CinemaLayout cinemaLayout) {
        TypeMap<CinemaLayout, CinemaLayoutDTO> typeMap = new ModelMapper().createTypeMap(CinemaLayout.class, CinemaLayoutDTO.class);
        return typeMap.map(cinemaLayout);
    }

}
