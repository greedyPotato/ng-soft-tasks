package com.tasks.tasksandusers.exception;



public class UserIdNotFoundException extends RuntimeException {
    public UserIdNotFoundException(String message) {
        super(message);
    }
    
}
