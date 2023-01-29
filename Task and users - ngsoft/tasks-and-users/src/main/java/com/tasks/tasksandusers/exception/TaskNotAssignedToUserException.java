package com.tasks.tasksandusers.exception;

public class TaskNotAssignedToUserException extends RuntimeException {
    public TaskNotAssignedToUserException(String message) {
        super(message);
    }
}