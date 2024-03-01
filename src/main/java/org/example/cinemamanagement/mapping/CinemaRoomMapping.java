package org.example.cinemamanagement.mapping;

import org.example.cinemamanagement.dto.CinemaRoomDTO;
import org.example.cinemamanagement.model.CinemaRoom;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

public class CinemaRoomMapping {
    public static CinemaRoomDTO convert(CinemaRoom cinemaRoom) {
        TypeMap<CinemaRoom, CinemaRoomDTO> typeMap = new ModelMapper().createTypeMap(CinemaRoom.class, CinemaRoomDTO.class);
        return typeMap.map(cinemaRoom);
    }
}
