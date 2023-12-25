package andrehsvictor.api.dailydo.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenDTO {
    private String username;
    private Date createdAt;
    private Date expiresAt;
    private String accessToken;
    private String refreshToken;
}
