package com.tasks.tasksandusers.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.tasks.tasksandusers.entity.Task;
import com.tasks.tasksandusers.entity.User;
import com.tasks.tasksandusers.exception.UserAlreadyExistsException;
import com.tasks.tasksandusers.exception.UserIdNotFoundException;
import com.tasks.tasksandusers.repository.TaskRepository;
import com.tasks.tasksandusers.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    @Override
    public List<User> getAllUsers() {
      
        return userRepository.findAll();
    }

    @Override
    public User addNewUser(User user) {
        
        user.setPassword(passwordEncoder(user.getPassword()));
        try{
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyExistsException("Username: "+user.getUserName()+" already exists");
          }
        
    }

    @Override
    public User updateUser(User user) {
        
        user.setPassword(passwordEncoder(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void removeUser(int userId) {
        if(userRepository.findById(userId).isPresent())
            userRepository.deleteById(userId);
        //else
            //throw exception
    }

    @Override
    public Task assignTaskToUser(int userId, int taskId) {
      Optional<User> user =  userRepository.findById(userId);
      Optional<Task> task =taskRepository.findById(taskId);
      if(!user.isPresent() || !task.isPresent()){
        throw new UserIdNotFoundException("User id is not found");
      }
      task.get().setAssignee(user.get());
      taskRepository.save(task.get());
        return task.get();
    }

    public static String passwordEncoder(String password){
        BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder();
		String encryptedPasswprd = cryptPasswordEncoder.encode(password);
        return encryptedPasswprd;
    }
    
}
