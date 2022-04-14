package io.simpolor.property.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class PropertyConfig {

    @Bean
    @ConfigurationProperties(prefix = "application")
    public Application application() {
        return new Application();
    }

    @Data
    public class Application {
        private String name;
        private String email;
    }
}
