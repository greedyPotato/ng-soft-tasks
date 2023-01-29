package com.tasks.tasksandusers.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tasks.tasksandusers.entity.Task;
import com.tasks.tasksandusers.entity.User;
import com.tasks.tasksandusers.services.UserService;

@RestController
public class UserController {
    
    @Autowired
    UserService userService;
    
    //ADMIN ONLY
    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers(){

        return ResponseEntity.ok(userService.getAllUsers());
    }
    //ADMIN ONLY
    @PostMapping("/user")
    public ResponseEntity<User> addNewUser(@RequestBody User user){
        
        return ResponseEntity.ok(userService.addNewUser(user));
        
    }
    //ADMIN ONLY
    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user){
        user.setId(id);
        User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }
    //ADMIN ONLY
    @DeleteMapping("user/{userId}")
    public void removeUser(@PathVariable int userId){

         userService.removeUser(userId);
        
    }
    //ADMIN ONLY
    @RequestMapping("/user/{userId}/task/{taskId}")
    public ResponseEntity<Task> assignTaskToUser(@PathVariable int userId, @PathVariable int taskId){
       return ResponseEntity.ok(userService.assignTaskToUser(userId, taskId));
    }
}
