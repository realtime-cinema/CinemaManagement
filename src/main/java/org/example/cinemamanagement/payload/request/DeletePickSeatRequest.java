package org.example.cinemamanagement.payload.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class DeletePickSeatRequest {
    private Integer x;
    private Integer y;
}
