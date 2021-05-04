package com.lbycpd2.todoexp.restful.security;

import com.lbycpd2.todoexp.restful.user.User;
import com.lbycpd2.todoexp.restful.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component("userSecurity")
@AllArgsConstructor
public class UserSecurity {
    private final UserService userService;
    public boolean hasUserId(Principal principal, String id){
        User user = userService.getUser(id);
        return principal.getName().equals(user.getEmail());
    }
}
