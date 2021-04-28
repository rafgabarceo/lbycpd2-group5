package com.lbycpd2.todoexp.restful.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBUserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository){
        return args -> {
            User user1 = new User(
                    "Gab",
                    "Arceo",
                    "nice@nice.com",
                    "chocolate",
                    UserRole.USER
            );
            User user2 = new User(
                    "Lauren",
                    "Sason",
                    "nice@nice.com",
                    "chocolate",
                    UserRole.USER
            );
            userRepository.save(user1);
            userRepository.save(user2);
        };
    }
}
