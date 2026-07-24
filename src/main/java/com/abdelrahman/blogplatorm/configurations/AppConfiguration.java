package com.abdelrahman.blogplatorm.configurations;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.abdelrahman.blogplatorm.entities.User;
import com.abdelrahman.blogplatorm.enums.Role;
import com.abdelrahman.blogplatorm.repositories.UserRepo;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableJpaAuditing
@RequiredArgsConstructor
public class AppConfiguration implements CommandLineRunner{

	private final UserRepo userRepo;
	private final PasswordEncoder encoder;
	@Override
	public void run(String... args) throws Exception {
		
		if(userRepo.findByEmail("admin@gmail.com").isEmpty()) {
			User user = new User();
			user.setEmail("admin@gmail.com");
			user.setPassword(encoder.encode("123456"));
			user.setRole(Role.ROLE_ADMIN);
			user.setUsername("admin123");
			userRepo.save(user);
		}
		
	}

	
}
