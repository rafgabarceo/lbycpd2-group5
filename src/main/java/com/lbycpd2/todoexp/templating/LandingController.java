package com.lbycpd2.todoexp.templating;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LandingController {

    @GetMapping(path ="/")
    public String getLandingPage(Model model){
        return "index";
    }
}
