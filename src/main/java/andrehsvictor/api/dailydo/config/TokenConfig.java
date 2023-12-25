package andrehsvictor.api.dailydo.config;

import java.util.Base64;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.auth0.jwt.algorithms.Algorithm;

import jakarta.annotation.PostConstruct;
import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "security.jwt.token")
public class TokenConfig {
    private Long expireLength;
    private String secretKey;
    private Algorithm algorithm;

    @PostConstruct
    private void configure() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }
}
