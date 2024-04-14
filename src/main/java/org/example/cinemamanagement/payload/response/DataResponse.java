package org.example.cinemamanagement.payload.response;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataResponse {
    private String message;
    private Object data;
}
