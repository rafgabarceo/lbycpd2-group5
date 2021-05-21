package com.lbycpd2.todoexp.restful.user.registration.confirmationtoken.controllers;

import com.lbycpd2.todoexp.restful.user.User;
import com.lbycpd2.todoexp.restful.user.UserService;
import com.lbycpd2.todoexp.restful.user.exceptions.ConfirmationTokenNotFoundException;
import com.lbycpd2.todoexp.restful.user.exceptions.UserAlreadyInDatabaseException;
import com.lbycpd2.todoexp.restful.user.registration.confirmationtoken.ConfirmationToken;
import com.lbycpd2.todoexp.restful.user.registration.confirmationtoken.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping(path = "/register")
@AllArgsConstructor
public class UserAddController {

    public final UserService userService;
    public final ConfirmationTokenService confirmationTokenService;

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user) throws UserAlreadyInDatabaseException, MessagingException {
        if(userService.checkIfEmailTaken(user.getEmail())){
            return new ResponseEntity<>("User already in database.", HttpStatus.BAD_REQUEST);
        }
        userService.addNewUser(user);
        return new ResponseEntity<>("User added. Please check email.", HttpStatus.OK);
    }

    @GetMapping(path = "/confirm/{token}")
    public ResponseEntity<String> confirmUser(@PathVariable(name = "token") String token) throws ConfirmationTokenNotFoundException {
        Optional<ConfirmationToken> confirmationToken = confirmationTokenService.getConfirmationToken(token);
        if(confirmationToken.isEmpty()){
            throw new ConfirmationTokenNotFoundException("Confirmation token not found");
        }

        confirmationTokenService.confirmUser(confirmationToken.get());
        return new ResponseEntity<>("Confirmed!", HttpStatus.OK);
    }
}
