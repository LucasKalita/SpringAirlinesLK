package com.lucaskalita.airlines.security.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse register(@RequestBody RegisterRequest registerRequest){
    return authenticationService.register(registerRequest);
    }
    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse register(@RequestBody AuthenticateRequest authenticateRequest){
    return authenticationService.authenticate(authenticateRequest);
    }
}
