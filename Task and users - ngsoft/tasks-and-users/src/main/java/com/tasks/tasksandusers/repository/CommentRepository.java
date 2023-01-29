package com.tasks.tasksandusers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tasks.tasksandusers.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer>{
    
}
