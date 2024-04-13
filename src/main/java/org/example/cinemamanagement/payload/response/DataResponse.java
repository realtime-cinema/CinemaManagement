package org.example.cinemamanagement.payload.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DataResponse {
    private String message;
    private Object data;
}
