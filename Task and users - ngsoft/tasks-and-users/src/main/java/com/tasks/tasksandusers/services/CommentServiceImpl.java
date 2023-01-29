package com.tasks.tasksandusers.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasks.tasksandusers.entity.Comment;
import com.tasks.tasksandusers.entity.Task;
import com.tasks.tasksandusers.entity.User;
import com.tasks.tasksandusers.repository.CommentRepository;
import com.tasks.tasksandusers.repository.TaskRepository;
import com.tasks.tasksandusers.repository.UserRepository;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment assignCommentToTask(Comment comment, int userId, int taskId) {
        Optional<Task> taskComment = taskRepository.findById(taskId);
        Optional<User> userComment = userRepository.findById(userId);

        if(taskComment.isPresent() && userComment.isPresent()){
            comment.setTask(taskComment.get());
            comment.setUser(userComment.get());
            commentRepository.save(comment);
        }else{
            //throw
        }
        return comment;
    }
    
}
