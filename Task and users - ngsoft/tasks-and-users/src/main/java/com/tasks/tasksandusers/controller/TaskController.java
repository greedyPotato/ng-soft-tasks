package com.tasks.tasksandusers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tasks.tasksandusers.entity.Task;
import com.tasks.tasksandusers.repository.TaskRepository;
import com.tasks.tasksandusers.services.TaskService;




@RestController
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TaskService taskService;

    //ADMIN and USER
    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks(Authentication authentication){
        
        return ResponseEntity.ok(taskService.getAllTasks(authentication.getName()));
    }

    //ADMIN ONLY
    @PostMapping("/task")
    public ResponseEntity<Task> addNewTask(@RequestBody Task task){
        
        return ResponseEntity.ok(taskService.addNewTask(task));
        
    }

    //ADMIN ONLY
    @PutMapping("/task")
    public ResponseEntity<Task> updateTask(@RequestBody Task task){

        return ResponseEntity.ok(taskService.updateTask(task));
    }

    //ADMIN and USER
    @RequestMapping("/task/completed/{taskId}")
    public ResponseEntity<Task> completeTask(@PathVariable int taskId,Authentication authentication){

        return ResponseEntity.ok(taskService.completeTask(taskId, authentication.getName()));
    }

    //ADMIN ONLY
    @DeleteMapping("task/{taskId}")
    public void removeTask(@PathVariable int taskId){

         taskService.removeTask(taskId);
    }
    
}
