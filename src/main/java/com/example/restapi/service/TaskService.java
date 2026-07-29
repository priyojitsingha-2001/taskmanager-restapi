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

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Task not found with id: "+id)
        );
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

    public Task updateTask(Long id, Task updatedTask) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Task not found with id: "+id));

        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setCompleted(updatedTask.getCompleted());

        return taskRepository.save(existingTask);
    }

    public Task patchTask(Long id, Task partialFields) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Task not found with id: "+id));

        if(partialFields.getTitle() != null){
            existingTask.setTitle(partialFields.getTitle());
        }
        if(partialFields.getCompleted() != null){
            existingTask.setCompleted(partialFields.getCompleted());
        }
        return taskRepository.save(existingTask);
    }

}
