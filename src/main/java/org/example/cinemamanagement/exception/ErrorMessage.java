package org.example.cinemamanagement.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class ErrorMessage {
    private String message;
    private int code;
    private HttpStatus status;
    @JsonProperty("time_stamp")
    private String timeStamp;

    public void setStatus(HttpStatus status) {
        this.status = status;
        this.code = status.value();
    }
}
