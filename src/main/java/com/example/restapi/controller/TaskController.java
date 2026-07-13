package com.example.restapi.controller;

import com.example.restapi.entity.Task;
import com.example.restapi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/welcome")
    public String getHelloMessage(){
        return "Welcome";
    }
    @GetMapping
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task){
        return taskService.createTask(task);
    }

    @DeleteMapping("/{id}")
    public void removeTask(@PathVariable Long id){
        taskService.removeTask(id);
    }
}
