package com.lbycpd2.todoexp.restful;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {
    private final MainService mainService;

    public UserController(MainService mainService){
        this.mainService = mainService;
    }

    @GetMapping
    public List<User> getUsers(){
        return mainService.getUsers();
    }

    @GetMapping(path="{user_id}")
    public User getOneUser(@PathVariable("user_id") Long userId){
        return mainService.getUser(userId);
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user){
        mainService.addUser(user);
    }

    @PostMapping(path="{user_id}/newparent")
    public void addPTask(@PathVariable("user_id") Long userId, @RequestBody ParentTask parentTask){
        mainService.addNewParentTask(userId, parentTask);
    }

    @DeleteMapping(path="{user_id}/{parent_id}")
    public void deletePTask(@PathVariable("user_id") Long userId, @PathVariable("parent_id") Long parent_id){
        mainService.deleteParentTask(userId, parent_id);
    }

    @DeleteMapping(path="{user_id}")
    public void deleteUser(@PathVariable("user_id") Long userId){
        mainService.deleteUser(userId);
    }

    //TODO: Fix PUT request
    @PutMapping(path="{user_id}")
    public void updateUser(@PathVariable("user_id") Long studentId,
                           @RequestParam(required = false) String username,
                           @RequestParam(required = false) String password,
                           @RequestParam(required = false) String email,
                           @RequestParam(required = false) Double experience){
        mainService.updateUser(studentId, username, password, email, experience);
    }
}
