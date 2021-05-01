package com.lbycpd2.todoexp.restful.user;

import com.lbycpd2.todoexp.restful.user.tasks.child.ChildTask;
import com.lbycpd2.todoexp.restful.user.tasks.parent.ParentTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBUserConfig {

    @Autowired
    private UserService userService;

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository){
        return args -> {
            User user1 = new User(
                    "USER",
                    "arceo.rafaelgabriel@outlook.com",
                    "Rafael Gabriel",
                    "Arceo",
                    "chocolate"
            );
            
            User user2 = new User(
                    "USER",
                    "lauresason@gmail.com",
                    "Lauren",
                    "Sason",
                    "chocolate"
            );

            User admin = new User(
                    "USER,ADMIN",
                    "admin@admin.com",
                    "admin",
                    "admin",
                    "admin"
            );

            ParentTask parentTask1 = new ParentTask("Parent", "This is a parent task");
            ChildTask childTask1 = new ChildTask("Child",
                    "This is a child task");


            parentTask1.addChildTask(childTask1);

            user1.addParentTask(parentTask1);

            userService.addNewUser(user1);
            userService.addNewUser(user2);
            userService.addNewUser(admin);
        };
    }
}
