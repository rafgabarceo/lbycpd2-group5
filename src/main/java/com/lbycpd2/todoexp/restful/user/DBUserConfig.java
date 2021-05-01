package com.lbycpd2.todoexp.restful.user;

import com.lbycpd2.todoexp.restful.user.tasks.child.ChildTask;
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
                    "nice0@nice.com",
                    "chocolate",
                    "USER,ADMIN"
            );
            
            User user2 = new User(
                    "Lauren",
                    "Sason",
                    "nice1@nice.com",
                    "chocolate",
                    "USER"
            );

            User admin = new User(
                    "admin",
                    "admin",
                    "admin@admin.com",
                    "admin",
                    "USER,ADMIN"
            );

            ParentTask parentTask1 = new ParentTask("Parent", "This is a parent task");
            ChildTask childTask1 = new ChildTask("Child",
                    "This is a child task");


            parentTask1.addChildTask(childTask1);

            user1.addParentTask(parentTask1);


            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(admin);
        };
    }
}
