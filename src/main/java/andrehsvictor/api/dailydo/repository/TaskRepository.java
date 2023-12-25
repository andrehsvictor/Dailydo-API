package andrehsvictor.api.dailydo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import andrehsvictor.api.dailydo.entity.Task;
import andrehsvictor.api.dailydo.entity.User;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);

    @Query("SELECT t FROM Task t WHERE t.user = :user AND t.id = :id")
    Optional<Task> findByIdAndUser(User user, Long id);
}
