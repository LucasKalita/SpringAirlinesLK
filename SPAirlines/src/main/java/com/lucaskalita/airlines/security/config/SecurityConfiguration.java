package com.lucaskalita.airlines.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JWTAutheticationFilter jwtAuthFiler;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//        http
//                .csrf()
//                .disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/v1/auth/**")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtAuthFiler, UsernamePasswordAuthenticationFilter.class);

        return http.authorizeHttpRequests(authorizeHttp -> {
                            authorizeHttp.requestMatchers("/").permitAll();
                            authorizeHttp.requestMatchers("/flights").permitAll();
                            authorizeHttp.requestMatchers("/airports").permitAll();
                            authorizeHttp.requestMatchers("/users").hasRole("ADMIN");
                            authorizeHttp.anyRequest().authenticated();
                        }
                ).formLogin(l -> l.defaultSuccessUrl("/loggedIn"))
                .logout(l -> l.logoutSuccessUrl("/loggedOut"))
                .oauth2Login(Customizer.withDefaults())
                .build();

    }
}
