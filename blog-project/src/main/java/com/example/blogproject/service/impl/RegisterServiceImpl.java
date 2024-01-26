package com.example.blogproject.service.impl;

import com.example.blogproject.entity.Role;
import com.example.blogproject.entity.User;
import com.example.blogproject.exception.BlogException;
import com.example.blogproject.payload.RegisterDto;
import com.example.blogproject.repository.RoleRepository;
import com.example.blogproject.repository.UserRepository;
import com.example.blogproject.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class RegisterServiceImpl implements RegisterService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public String register(RegisterDto registerDto) {
        //username validation
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new BlogException(HttpStatus.BAD_REQUEST,"Usename already exists");
        }
         //email validation
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new BlogException(HttpStatus.BAD_REQUEST,"Email already exists");
        }

        User user= new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setUsername(registerDto.getUsername());

        Set<Role> roles = new HashSet<>();
        Role userRole =roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        return "User registered";

    }
}
