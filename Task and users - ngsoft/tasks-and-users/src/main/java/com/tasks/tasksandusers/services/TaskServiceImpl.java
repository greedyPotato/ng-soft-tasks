package com.tasks.tasksandusers.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import com.tasks.tasksandusers.domain.TaskStatus;
import com.tasks.tasksandusers.entity.Task;
import com.tasks.tasksandusers.exception.TaskNotAssignedToUserException;
import com.tasks.tasksandusers.exception.TaskNotFoundException;
import com.tasks.tasksandusers.repository.TaskRepository;
import com.tasks.tasksandusers.repository.UserRepository;

@Service
public class TaskServiceImpl implements TaskService{


    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;
   
    
    @Override
    public List<Task> getAllTasks(String userName) {
        //user retrieves his assigned list of tasks with comments
       
     
       List<Task> userTasks = taskRepository.findAll();
       List<Task> tasksReponse = userTasks.stream().filter(task -> !task.getStatus().equals(TaskStatus.ARCHIVED) && task.getAssignee().getUserName().equals(userName)).collect(Collectors.toList());
        return tasksReponse;
    }

    @Override
    public Task addNewTask(Task task) {
     
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Task task) {
     
        return taskRepository.save(task);
    }

    @Override
    public Task completeTask(int taskId, String userName) {
 
        Optional<Task> taskById = taskRepository.findById(taskId);
        if(!taskById.isPresent())
        {
            throw new TaskNotFoundException("Task id is not found");
        }
        if(!taskById.get().getAssignee().getUserName().equals(userName)){
            throw new TaskNotAssignedToUserException("This is not the User's Task");
        }
        taskById.get().setStatus(TaskStatus.COMPLETED);
        taskRepository.save(taskById.get());
        return taskById.get();
    }

    @Override
    public void removeTask(int taskId) {

        taskRepository.deleteById(taskId);
        
    }
    
}
