package com.tasks.tasksandusers.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tasks.tasksandusers.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{
    
    Optional<List<Task>> findAllByAssignee(final int userId);
}
