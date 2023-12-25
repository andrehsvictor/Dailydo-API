package andrehsvictor.api.dailydo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import andrehsvictor.api.dailydo.dto.AccountCredentialsDTO;
import andrehsvictor.api.dailydo.dto.RefreshTokenDTO;
import andrehsvictor.api.dailydo.dto.TokenDTO;
import andrehsvictor.api.dailydo.entity.User;

@Service
public class AuthService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserService userService;

    public TokenDTO doLogin(AccountCredentialsDTO accountCredentials) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                accountCredentials.getUsername(), accountCredentials.getPassword());
        Authentication auth = manager.authenticate(authenticationToken);
        return tokenService.generateToken((User) auth.getPrincipal());
    }

    public TokenDTO doRefresh(RefreshTokenDTO refreshToken) {
        return tokenService.generateToken(refreshToken);
    }

    public TokenDTO doSignup(AccountCredentialsDTO accountCredentials) {
        userService.save(accountCredentials);
        return doLogin(accountCredentials);
    }
}
