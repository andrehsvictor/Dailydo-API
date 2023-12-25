package andrehsvictor.api.dailydo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import andrehsvictor.api.dailydo.dto.AccountCredentialsDTO;
import andrehsvictor.api.dailydo.entity.User;
import andrehsvictor.api.dailydo.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username \"" + username + "\" not found"));
    }

    public User save(AccountCredentialsDTO accountCredentials) {
        repository.findByUsername(accountCredentials.getUsername()).ifPresent(
                (u) -> new RuntimeException("Username \"" + u.getUsername() + "\" already exists"));
        User user = new User(accountCredentials.getUsername(), accountCredentials.getPassword());
        return repository.save(user);
    }
}
