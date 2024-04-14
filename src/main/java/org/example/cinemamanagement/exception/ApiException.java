package org.example.cinemamanagement.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
