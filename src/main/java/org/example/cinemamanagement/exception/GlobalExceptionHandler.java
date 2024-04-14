package org.example.cinemamanagement.exception;

import org.example.cinemamanagement.utils.DateTimeHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    // handle other RUNTIME exception
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex) {

        ErrorMessage error = new ErrorMessage();
        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setMessage(ex.getMessage());
        error.setTimeStamp(LocalDateTime.now().format(DateTimeHelper.formatVNTime()));

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // handle NOT_FOUND exception
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException ex) {

        ErrorMessage error = new ErrorMessage();
        error.setStatus(HttpStatus.NOT_FOUND);
        error.setMessage(ex.getMessage());
        error.setTimeStamp(LocalDateTime.now().format(DateTimeHelper.formatVNTime()));

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // handle API exception
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException ex) {

        ErrorMessage error = new ErrorMessage();
        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setMessage(ex.getMessage());
        error.setTimeStamp(LocalDateTime.now().format(DateTimeHelper.formatVNTime()));

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


}

