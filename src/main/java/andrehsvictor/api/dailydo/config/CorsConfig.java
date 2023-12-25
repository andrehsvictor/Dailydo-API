package andrehsvictor.api.dailydo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "cors")
public class CorsConfig {
    private String allowedOrigins;
}
