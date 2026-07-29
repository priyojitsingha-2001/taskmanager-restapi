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

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task){
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask){
        return taskService.updateTask(id,updatedTask);
    }

    @PatchMapping("/{id}")
    public Task patchTask(@PathVariable Long id, Task partialFields){
        return taskService.patchTask(id, partialFields);
    }

    @DeleteMapping("/{id}")
    public void removeTask(@PathVariable Long id){
        taskService.removeTask(id);
    }
}
