package com.lbycpd2.todoexp.restful.user;

import com.lbycpd2.todoexp.restful.user.exceptions.TaskNotFoundException;
import com.lbycpd2.todoexp.restful.user.tasks.parent.ParentModelAssembler;
import com.lbycpd2.todoexp.restful.user.tasks.parent.ParentTask;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;
    private final UserModelAssembler userModelAssembler;
    private final ParentModelAssembler parentModelAssembler;

    @GetMapping
    public CollectionModel<EntityModel<User>> getAllUsers(){
        List<EntityModel<User>> users = userService
                .getUsers()
                .stream()
                .map(userModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(users, linkTo(methodOn(UserService.class).getUsers()).withSelfRel());
    }

    @GetMapping(path="{id}")
    public EntityModel<User> getUser(@PathVariable(name = "id") Long id){
        User user = userService.getUser(id);
        return userModelAssembler.toModel(user);
    }


    @GetMapping(path="{id}/tasks")
    public CollectionModel<EntityModel<ParentTask>> getUserParentTasks(@PathVariable(name = "id") Long id){
        List<EntityModel<ParentTask>> ptasks = userService
                .getParentTasks()
                .stream()
                .map(parentModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(ptasks, linkTo(methodOn(UserService.class).getParentTasks()).withSelfRel());
    }


    @GetMapping(path = "{id}/{parent_id}}")
    public EntityModel<ParentTask> getParentTask(@PathVariable(name = "parent_id") Long parent_id) throws TaskNotFoundException {
        ParentTask ptask = userService.getParentTask(parent_id);
        return parentModelAssembler.toModel(ptask);
    }
}


