package com.lbycpd2.todoexp.restful;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseConfiguration {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository){
        return args -> {
            User gabarceo = new User(
                    "gabarceo",
                    "arceo.rafaelgabriel@gmail.com",
                    "123"
            );
            userRepository.save(gabarceo);
        };
    }
}
