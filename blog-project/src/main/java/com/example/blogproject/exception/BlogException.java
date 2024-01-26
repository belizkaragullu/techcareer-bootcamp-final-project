package com.example.blogproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BlogException extends RuntimeException{

    private HttpStatus status;
    private String message;

    public BlogException(String message) {
       super(message);
    }
    public BlogException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
