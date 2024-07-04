package com.lucaskalita.airlines.security.backup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//       http.authorizeHttpRequests(authorize -> authorize
//               .requestMatchers("/users/**").permitAll()
//               .requestMatchers("/planes/**").permitAll()
//               .requestMatchers("/message/**").permitAll()
//               .requestMatchers("/flights/**").permitAll()
//               .requestMatchers("/tickets/**").permitAll()
//               .requestMatchers("/store/**").permitAll()
//               .requestMatchers("/airports/**").permitAll()
//               .requestMatchers("/addresses/**").permitAll()
//
//
//       ).csrf(Customizer.withDefaults());
//        return http.build();
//    }
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//}