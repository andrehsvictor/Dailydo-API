package andrehsvictor.api.dailydo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import andrehsvictor.api.dailydo.config.TokenConfig;
import andrehsvictor.api.dailydo.dto.RefreshTokenDTO;
import andrehsvictor.api.dailydo.dto.TokenDTO;
import andrehsvictor.api.dailydo.entity.User;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class TokenService {

    @Autowired
    private TokenConfig config;

    @Autowired
    private UserService userService;

    private static final String BEARER_PREFIX = "Bearer ";

    public TokenDTO generateToken(User user) {
        String username = user.getUsername();
        Date now = new Date();
        Date validity = new Date(now.getTime() + config.getExpireLength());
        String accessToken = getAccessToken(username, now, validity);
        String refreshToken = getRefreshToken(username, now);
        return new TokenDTO(username, now, validity, accessToken, refreshToken);
    }

    public TokenDTO generateToken(RefreshTokenDTO refreshToken) {
        if (!validateToken(refreshToken.getRefreshToken()))
            throw new RuntimeException("Invalid or expired refresh token: " + refreshToken.getRefreshToken());
        DecodedJWT decodedJWT = decodedToken(refreshToken.getRefreshToken());
        User user = (User) userService.loadUserByUsername(decodedJWT.getSubject());
        return generateToken(user);
    }

    public User getUserFromBearerToken(String bearerToken) {
        String token = bearerToken.replace(BEARER_PREFIX, "");
        DecodedJWT decodedJWT = decodedToken(token);
        User user = (User) userService.loadUserByUsername(decodedJWT.getSubject());
        return user;
    }

    private String getRefreshToken(String username, Date now) {
        Date refreshTokenExpiration = new Date(now.getTime() + 1200000L);
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(now)
                .withExpiresAt(refreshTokenExpiration)
                .withIssuer("Dailydo API")
                .sign(config.getAlgorithm());
    }

    private String getAccessToken(String username, Date now, Date validity) {
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withIssuer("Dailydo API")
                .sign(config.getAlgorithm());
    }

    private DecodedJWT decodedToken(String token) {
        return JWT.require(config.getAlgorithm()).build().verify(token);
    }

    public Authentication getAuthentication(String token) {
        DecodedJWT decodedJWT = decodedToken(token);
        UserDetails userDetails = userService.loadUserByUsername(decodedJWT.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith(BEARER_PREFIX))
            return bearerToken.replace(BEARER_PREFIX, "");
        return null;
    }

    public Boolean validateToken(String token) {
        try {
            DecodedJWT decodedJWT = decodedToken(token);
            return !decodedJWT.getExpiresAt().before(new Date());
        } catch (Exception e) {
            throw new RuntimeException("Invalid or expired access token: " + token);
        }
    }
}
