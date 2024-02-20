package org.example.cinemamanagement.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddManagerRequest {
    @JsonProperty("email_user")
    private String emailUser;
    @JsonProperty("id_cinema")
    private UUID idCinema;
}
