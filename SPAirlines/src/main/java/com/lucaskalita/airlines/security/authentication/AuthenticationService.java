package com.lucaskalita.airlines.security.authentication;

import com.lucaskalita.airlines.security.config.JWTService;
import com.lucaskalita.airlines.users.AccountType;
import com.lucaskalita.airlines.users.User;
import com.lucaskalita.airlines.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var user = User.builder()
                .name(registerRequest.getName())
                .surname(registerRequest.getSurname())
                .email(registerRequest.getEmail())
                .username(registerRequest.getUsername())
                .socialSecurityNumber(registerRequest.getSocialSecurityNumber())
                .accountBalance(BigDecimal.valueOf(0))
                .dateOfBirth(registerRequest.getBirthDate())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .accountType(AccountType.REGISTERED)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticateRequest authenticateRequest) {

    }
}
