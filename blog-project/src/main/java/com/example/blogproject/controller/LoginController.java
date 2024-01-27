package com.example.blogproject.controller;

import com.example.blogproject.payload.LoginDto;
import com.example.blogproject.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/auth")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(LoginDto loginDto){
        String response = loginService.login(loginDto);
        return ResponseEntity.ok(response);
    }
}
