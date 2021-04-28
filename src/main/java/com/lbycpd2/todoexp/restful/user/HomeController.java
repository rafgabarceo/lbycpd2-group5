package com.lbycpd2.todoexp.restful.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(name = "/home")
public class HomeController {

    @GetMapping
    public String getHome(Principal principal){
        return "This is " + principal.getName() + "'s house!";
    }
}
