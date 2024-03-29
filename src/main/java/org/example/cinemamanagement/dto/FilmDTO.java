package org.example.cinemamanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilmDTO {
    private UUID id;
    private String title;
    private String director;
    @JsonProperty("release_date")
    private Timestamp releaseDate;
    private String country;
    @JsonProperty("restrict_age")
    private Integer restrictAge;
    private List<TagDTO> tags;


}
