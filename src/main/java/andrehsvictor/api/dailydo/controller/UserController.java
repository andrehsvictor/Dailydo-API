package andrehsvictor.api.dailydo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import andrehsvictor.api.dailydo.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/user")
    public ResponseEntity<?> findCurrentUser() {
        return ResponseEntity.ok(service.findCurrentUser());
    }
}
