package com.tailerbox.backend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "routes")
public class RouteConfigProvider {
    private String apiVersion;
    private String apiBaseUri;
}
