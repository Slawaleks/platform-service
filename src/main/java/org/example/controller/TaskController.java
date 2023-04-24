package org.example.controller;

import org.example.handler.TaskHandler;
import org.example.model.Task;

import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/task")

public class TaskController {
    private final TaskHandler taskHandler = new TaskHandler();


    @PostMapping("/")
    public long createTask(@RequestBody Task task) throws SQLException {
        return taskHandler.createTask(task);
    }

    @PutMapping("/{id}")
    public void updateTask(@RequestBody Task task, @PathVariable Long id) throws SQLException {
        taskHandler.updateTask(task, id);
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id) throws SQLException {
        return taskHandler.getTask(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) throws SQLException {
        taskHandler.deleteTask(id);
    }
}

