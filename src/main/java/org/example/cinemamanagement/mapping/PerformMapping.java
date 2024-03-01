package org.example.cinemamanagement.mapping;

import org.example.cinemamanagement.dto.CinemaDTO;
import org.example.cinemamanagement.dto.CinemaLayoutDTO;
import org.example.cinemamanagement.dto.PerformDTO;
import org.example.cinemamanagement.model.Perform;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

public class PerformMapping {
    public static PerformDTO convert(Perform perform) {
        TypeMap<Perform, PerformDTO> typeMap = new ModelMapper().createTypeMap(Perform.class, PerformDTO.class);

        typeMap.addMappings(mapper -> {
            mapper.map(Perform::getCinemaRoom, PerformDTO::setCinemaRoomDTO);
            mapper.map(Perform::getFilm, PerformDTO::setFilmDTO);
            mapper.map(Perform::getViewType, PerformDTO::setViewTypeDTO);
            mapper.map(Perform::getTranslateType, PerformDTO::setTranslateTypeDTO);
        });
//        i don't know why losing the value of cinema in cinemaroomDTO when mapping?


        PerformDTO performDTO = typeMap.map(perform);
        performDTO.getCinemaRoomDTO().setCinemaDTO(CinemaDTO.builder().
                id(perform.getCinemaRoom().getCinema().getId())
                .name(perform.getCinemaRoom().getCinema().getName())
                .variant(perform.getCinemaRoom().getCinema().getVariant())
                .build());

        performDTO.getCinemaRoomDTO().setCinemaLayoutDTO(
                CinemaLayoutDTO.builder()
                        .id(perform.getCinemaRoom().getCinemaLayout().getId())
                        .xIndex(perform.getCinemaRoom().getCinemaLayout().getXIndex())
                        .yIndex(perform.getCinemaRoom().getCinemaLayout().getYIndex())
                        .build()
        );
        return performDTO;
    }


    public static Perform convert(PerformDTO performDTO) {
        TypeMap<PerformDTO, Perform> typeMap = new ModelMapper()
                .createTypeMap(PerformDTO.class, Perform.class);
        typeMap.addMappings(mapper -> {
            mapper.map(PerformDTO::getCinemaRoomDTO, Perform::setCinemaRoom);
            mapper.map(PerformDTO::getFilmDTO, Perform::setFilm);
            mapper.map(PerformDTO::getViewTypeDTO, Perform::setViewType);
            mapper.map(PerformDTO::getTranslateTypeDTO, Perform::setTranslateType);
        });
        return typeMap.map(performDTO);
    }
}
