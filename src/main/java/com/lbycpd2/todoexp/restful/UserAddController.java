package com.lbycpd2.todoexp.restful;

import com.lbycpd2.todoexp.restful.user.User;
import com.lbycpd2.todoexp.restful.user.UserService;
import com.lbycpd2.todoexp.restful.user.exceptions.UserAlreadyInDatabaseException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/newuser")
@AllArgsConstructor
public class UserAddController {

    public final UserService userService;

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user) throws UserAlreadyInDatabaseException {
        if(userService.checkIfEmailTaken(user.getEmail())){
            return new ResponseEntity<>("User already in database.", HttpStatus.BAD_REQUEST);
        }
        userService.addNewUser(user);
        return new ResponseEntity<>("User added.", HttpStatus.OK);
    }
}
