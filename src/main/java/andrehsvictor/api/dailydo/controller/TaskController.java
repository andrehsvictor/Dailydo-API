package andrehsvictor.api.dailydo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import andrehsvictor.api.dailydo.dto.TaskDTO;
import andrehsvictor.api.dailydo.service.TaskService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping("/task/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        log.info("Finding task with id: " + id);
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/tasks")
    public ResponseEntity<?> findAll() {
        log.info("Finding tasks from current user");
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/task/create")
    public ResponseEntity<?> create(@RequestBody TaskDTO taskDTO) {
        log.info("Creating new task");
        return new ResponseEntity<>(service.save(taskDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/task/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        log.info("Deleting task with id: " + id);
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/task/update/{id}")
    public ResponseEntity<?> update(@RequestBody TaskDTO taskDTO, @PathVariable Long id) {
        log.info("Updating task with id: " + id);
        return ResponseEntity.ok(service.update(id, taskDTO));
    }

}
