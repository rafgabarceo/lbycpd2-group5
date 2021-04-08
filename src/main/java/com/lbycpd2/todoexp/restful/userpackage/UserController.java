package com.lbycpd2.todoexp.restful.userpackage;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserRepository repository;

    public UserController(UserRepository repository){
        this.repository = repository;
    }


}
