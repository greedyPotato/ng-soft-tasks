package com.tasks.tasksandusers.services;

import java.util.List;
import org.springframework.stereotype.Service;

import com.tasks.tasksandusers.entity.Task;
import com.tasks.tasksandusers.entity.User;

@Service
public interface UserService {

   
  
    public List<User> getAllUsers();

    public User addNewUser (User user);

    public User updateUser( User user);

    public void removeUser (int userId);

    public Task assignTaskToUser(int userId, int taskId);
    
}
