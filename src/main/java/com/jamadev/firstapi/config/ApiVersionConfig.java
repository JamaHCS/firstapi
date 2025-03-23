package com.jamadev.firstapi.config;

import com.jamadev.firstapi.dto.global.Result;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiVersionConfig {

    @Value("${api.version}")
    private String apiVersion;

    @PostConstruct
    public void init() {
        Result.setAPI_VERSION(apiVersion);
    }
}
