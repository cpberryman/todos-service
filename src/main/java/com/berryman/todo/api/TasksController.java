package com.berryman.todo.api;

import com.berryman.todo.model.Task;
import com.berryman.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    private final TaskRepository taskRepository;

    @Autowired
    public TasksController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/find/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Task> findTaskById(@PathVariable Integer id) {
        Optional<Task> task = taskRepository.findById(id);
        return new ResponseEntity<>(task.get(), HttpStatus.OK);
    }

    @GetMapping("/find/all")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Iterable<Task>> findAllTasks() {
        Iterable<Task> tasks = taskRepository.findAll();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping("/add")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        Task added = taskRepository.save(task);
        return new ResponseEntity<>(added, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Task> updateTask(@PathVariable Integer id, @RequestBody Task task) {
        Task toUpdate = taskRepository.findById(id).get();
        toUpdate.setDescription(task.getDescription());
        toUpdate.setComplete(task.isComplete());

        Task updated = taskRepository.save(toUpdate);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Task> deleteTask(@PathVariable Integer id) {
        taskRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
