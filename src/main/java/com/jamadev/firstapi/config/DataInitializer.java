package com.jamadev.firstapi.config;

import com.jamadev.firstapi.models.User;
import com.jamadev.firstapi.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner seedData(IUserRepository userRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                userRepository.save(new User("John Doe", "john@example.com", "test"));
                userRepository.save(new User("Jane Smith", "jane@example.com", "test"));
            }
        };
    }
}
