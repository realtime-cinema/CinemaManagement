package org.example.cinemamanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
        @ExceptionHandler(RuntimeException.class)
        @ResponseStatus(value = HttpStatus.NOT_FOUND)
        public ErrorMessage handleNotFoundException(Exception ex) {
            return new ErrorMessage(404, ex.getLocalizedMessage());
        }

}
