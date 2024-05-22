package ru.kpfu.itis.skillshare.loggingstarter.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kpfu.itis.skillshare.loggingstarter.aspect.RestControllerLoggingAspect;

@Configuration
public class LoggingConfig {

    @Bean
    @ConditionalOnProperty(name = "logger.rest-controllers.enabled", havingValue = "true")
    public RestControllerLoggingAspect restControllerLoggingAspect() {
        return new RestControllerLoggingAspect();
    }
}
