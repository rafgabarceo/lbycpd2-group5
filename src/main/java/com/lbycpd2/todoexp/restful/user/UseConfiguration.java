package com.lbycpd2.todoexp.restful.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseConfiguration {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository){
        return args -> {
            User gabarceo = new User(
                    "gab",
                    "arceo",
                    "arceo.rafaelgabriel@gmail.com",
                    "password",
                    UserRole.USER
            );
            userRepository.save(gabarceo);
        };
    }
}
