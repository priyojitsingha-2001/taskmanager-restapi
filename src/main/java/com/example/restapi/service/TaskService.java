package com.example.restapi.service;

import com.example.restapi.entity.Task;
import com.example.restapi.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Task createTask(@RequestBody Task task){
        if(task.getTitle() == null || task.getTitle().isEmpty()){
            throw new IllegalArgumentException("Task title cannot be Empty!");
        }
        else{
            return taskRepository.save(task);
        }
    }

    public void removeTask(Long id){
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        taskRepository.delete(existingTask);
//        taskRepository.deleteById(id);
    }
}
