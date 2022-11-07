package com.example.backend.controller;


import com.example.backend.entity.JwtResponse;
import com.example.backend.service.JwtService;
import com.example.backend.entity.JwtRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtController {
    @Autowired
    private JwtService jwtService;

    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        // System.out.println("(controller/jwtController)"+ jwtRequest.getUserName());
        return jwtService.createJwtToken(jwtRequest);
    }
}
