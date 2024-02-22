package org.example.cinemamanagement.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorMessage {
    private int statusCode;
    private String message;
}
