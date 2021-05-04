package com.lbycpd2.todoexp;

import com.lbycpd2.todoexp.restful.user.User;
import com.lbycpd2.todoexp.restful.user.UserRepository;
import com.lbycpd2.todoexp.restful.user.UserService;
import com.lbycpd2.todoexp.restful.user.tasks.child.ChildTask;
import com.lbycpd2.todoexp.restful.user.tasks.parent.ParentTask;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class DBUserConfig {

    private final UserService userService;

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository){
        return args -> {
            User user1 = new User(
                    "USER",
                    "rafael_gabriel_arceo@dlsu.edu.ph",
                    "Rafael Gabriel",
                    "Arceo",
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
            userService.addNewUser(admin);
        };
    }
}
