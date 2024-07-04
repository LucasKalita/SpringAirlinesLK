package com.lucaskalita.airlines.security.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @PostMapping("/register")
    public ResponseEntity<AutenticationResponse> register(@RequestBody RegisterRequest registerRequest){

    }
    @PostMapping("/authenticate")
    public ResponseEntity<AutenticationResponse> register(@RequestBody AuthenticateRequest authenticateRequest){

    }
}
