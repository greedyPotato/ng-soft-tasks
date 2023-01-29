package com.tasks.tasksandusers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tasks.tasksandusers.entity.Comment;
import com.tasks.tasksandusers.services.CommentService;

@RestController
public class CommentsController {
    
    @Autowired
    CommentService commentService;

    @PostMapping("/comment/user/{userId}/task/{taskId}")
    public ResponseEntity<Comment> assignCommentToTask(@RequestBody Comment comment, @PathVariable int userId, @PathVariable int taskId){
       
       return ResponseEntity.ok(commentService.assignCommentToTask(comment, userId, taskId));
    }

    }

