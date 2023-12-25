package andrehsvictor.api.dailydo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import andrehsvictor.api.dailydo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
