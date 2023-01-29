package com.tasks.tasksandusers.services;

import java.util.List;

import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;

import com.tasks.tasksandusers.entity.Task;

@Service
public interface TaskService {

    public List<Task> getAllTasks(String userName);
   
    public Task addNewTask(@RequestBody Task task);

    public Task updateTask(@RequestBody Task task);

    public Task completeTask(@PathVariable int taskId, String userName);

    public void removeTask(@PathVariable int taskId);
}
