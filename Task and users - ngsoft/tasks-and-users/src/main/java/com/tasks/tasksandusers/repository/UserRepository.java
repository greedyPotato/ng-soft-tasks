package com.tasks.tasksandusers.repository;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tasks.tasksandusers.entity.User;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    
    Optional<User> findByUserName(final String userName);

    @Transactional
    User save(User user) throws DataIntegrityViolationException;
}
