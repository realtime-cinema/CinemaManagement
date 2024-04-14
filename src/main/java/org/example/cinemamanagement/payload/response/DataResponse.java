package org.example.cinemamanagement.payload.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class DataResponse {
    private String message;
    private int code;
    private HttpStatus status;
    private Object data;

    public void setStatus(HttpStatus status) {
        this.status = status;
        this.code = status.value();
    }
}
