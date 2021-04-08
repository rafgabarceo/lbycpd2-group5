package com.lbycpd2.todoexp.restful.userpackage;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {
    private final UserRepository repository;
    private final UserService userService;

    public UserController(UserRepository repository, UserService userService){
        this.repository = repository;
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping(path="{user_id}")
    public User getOneUser(@PathVariable("user_id") Long userId){
        return userService.getUser(userId);
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user){
        userService.addUser(user);
    }

    @DeleteMapping(path="{user_id}")
    public void deleteUser(@PathVariable("user_id") Long userId){
        userService.deleteUser(userId);
    }

    //TODO: Fix PUT request
    @PutMapping(path="{user_id}")
    public void updateUser(@PathVariable("user_id") Long studentId,
                           @RequestParam(required = false) String username,
                           @RequestParam(required = false) String password,
                           @RequestParam(required = false) String email,
                           @RequestParam(required = false) Double experience){
        userService.updateUser(studentId, username, password, email, experience);
    }

}
