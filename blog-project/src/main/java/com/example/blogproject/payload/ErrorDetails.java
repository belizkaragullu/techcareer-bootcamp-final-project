package com.example.blogproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Date;
@AllArgsConstructor
@Getter
public class ErrorDetails {

    private Date timeStamp;
    private String message;
    private String details;

}
