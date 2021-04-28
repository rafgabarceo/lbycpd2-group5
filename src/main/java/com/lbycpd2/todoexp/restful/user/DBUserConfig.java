package com.lbycpd2.todoexp.restful.user;

import com.lbycpd2.todoexp.restful.user.tasks.parent.ParentTask;
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

            ParentTask parentTask1 = new ParentTask();
            parentTask1.setUser(user1);
            parentTask1.setTitle("Hello!");
            parentTask1.setDescription("World!");
            user1.addParentTask(parentTask1);
            userRepository.save(user1);
            userRepository.save(user2);
        };
    }
}
