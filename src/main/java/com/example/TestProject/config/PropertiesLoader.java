package com.example.TestProject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class PropertiesLoader {

    @Value("${security.header}")
    private String securityHeader;

    @Value("${security.key}")
    private String securityKey;
}
