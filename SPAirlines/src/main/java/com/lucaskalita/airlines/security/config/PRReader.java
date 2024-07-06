package com.lucaskalita.airlines.security.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Getter
@PropertySource("classpath:wuwuzela.properties")
public class PRReader {
    @Value("${guitar}")
    private String key;


}
