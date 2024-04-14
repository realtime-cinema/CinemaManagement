package org.example.cinemamanagement.exception;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import lombok.Getter;

@Getter
public class ExpiredTokenException extends ExpiredJwtException{

    public ExpiredTokenException(Header header, Claims claims, String message) {
        super(header, claims, message);
    }

}
