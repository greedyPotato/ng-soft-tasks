package com.tasks.tasksandusers.services;

import org.springframework.stereotype.Service;

import com.tasks.tasksandusers.entity.Comment;

@Service
public interface CommentService {

    public Comment assignCommentToTask(Comment comment, int userId, int taskId);
    
}
