package andrehsvictor.api.dailydo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import andrehsvictor.api.dailydo.dto.TaskDTO;
import andrehsvictor.api.dailydo.entity.Task;
import andrehsvictor.api.dailydo.entity.User;
import andrehsvictor.api.dailydo.repository.TaskRepository;
import andrehsvictor.api.dailydo.util.ObjectMapper;
import andrehsvictor.api.dailydo.util.CurrentUserProvider;
import andrehsvictor.api.dailydo.util.Status;
import andrehsvictor.api.dailydo.util.EntityUpdater;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public TaskDTO findById(Long id) {
        User user = CurrentUserProvider.currentUser();
        Task entity = repository.findByIdAndUser(user, id)
                .orElseThrow(() -> new RuntimeException("Task with id " + id + " not found"));
        return ObjectMapper.map(entity, TaskDTO.class);
    }

    public List<TaskDTO> findAll() {
        User user = CurrentUserProvider.currentUser();
        return ObjectMapper.mapList(repository.findByUser(user), TaskDTO.class);
    }

    public TaskDTO save(TaskDTO taskDTO) {
        User user = CurrentUserProvider.currentUser();
        Task entity = ObjectMapper.map(taskDTO, Task.class);
        if (entity.getStatus() == null)
            entity.setStatus(Status.TO_DO);
        entity.setUser(user);
        repository.save(entity);
        return ObjectMapper.map(entity, TaskDTO.class);
    }

    public void deleteById(Long id) {
        User user = CurrentUserProvider.currentUser();
        Task entity = repository.findByIdAndUser(user, id)
                .orElseThrow(() -> new RuntimeException("Task with id " + id + " not found"));
        repository.delete(entity);
    }

    public TaskDTO update(Long id, TaskDTO taskDTO) {
        User user = CurrentUserProvider.currentUser();
        Task entity = repository.findByIdAndUser(user, id)
                .orElseThrow(() -> new RuntimeException("Task with id " + id + " not found"));
        entity = EntityUpdater.updatePartialy(taskDTO, entity);
        repository.save(entity);
        return ObjectMapper.map(entity, TaskDTO.class);
    }

}
