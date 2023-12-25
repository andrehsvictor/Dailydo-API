package andrehsvictor.api.dailydo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import andrehsvictor.api.dailydo.dto.AccountCredentialsDTO;
import andrehsvictor.api.dailydo.dto.RefreshTokenDTO;
import andrehsvictor.api.dailydo.service.AuthService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/login")
    public ResponseEntity<?> doLogin(@RequestBody AccountCredentialsDTO accountCredentials) {
        log.info("Doing login");
        return ResponseEntity.ok(service.doLogin(accountCredentials));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> doRefresh(@RequestBody RefreshTokenDTO refreshToken) {
        log.info("Doing refresh");
        return ResponseEntity.ok(service.doRefresh(refreshToken));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> doSignup(@RequestBody AccountCredentialsDTO accountCredentials) {
        log.info("Doing signup");
        return new ResponseEntity<>(service.doSignup(accountCredentials), HttpStatus.CREATED);
    }
}
