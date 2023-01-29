package com.tasks.tasksandusers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.tasks.tasksandusers.domain.UserRole;
import com.tasks.tasksandusers.entity.User;
import com.tasks.tasksandusers.repository.UserRepository;
import com.tasks.tasksandusers.services.UserServiceImpl;


@SpringBootApplication
public class TasksAndUsersApplication implements CommandLineRunner{

	@Autowired
	UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(TasksAndUsersApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {

		User defaultAdmin = new User();
			defaultAdmin.setActive(true);
			defaultAdmin.setPassword(UserServiceImpl.passwordEncoder("admin"));
			defaultAdmin.setUserName("admin");
			defaultAdmin.setRole(UserRole.ADMIN);
			defaultAdmin.setEmail("admin@ng-soft.com");

			userRepository.save(defaultAdmin);
		
	}

}
