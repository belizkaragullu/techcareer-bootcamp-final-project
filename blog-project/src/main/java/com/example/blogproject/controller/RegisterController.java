package com.example.blogproject.controller;

import com.example.blogproject.payload.RegisterDto;
import com.example.blogproject.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/auth")
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping(value ={"/register", "/login"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){ //convert a json into a java object
        String response = registerService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
