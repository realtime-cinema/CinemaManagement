package org.example.cinemamanagement.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class AddPaymentRequest {
    @JsonProperty("cinema_id")
    private UUID cinemaId;

    private Long amount;
}