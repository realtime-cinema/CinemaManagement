package org.example.cinemamanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class ErrorMessage {
    private int statusCode;
    private String message;
}
