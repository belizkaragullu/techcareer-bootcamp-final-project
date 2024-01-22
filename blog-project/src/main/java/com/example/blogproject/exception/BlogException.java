package com.example.blogproject.exception;

import org.springframework.http.HttpStatus;

public class BlogException extends RuntimeException{

    private HttpStatus status;

    public BlogException(HttpStatus status, String message) {
       super(message);
       this.status = status;
    }
}
