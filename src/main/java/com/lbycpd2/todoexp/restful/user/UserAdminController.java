package com.lbycpd2.todoexp.restful.user;

import com.lbycpd2.todoexp.restful.user.exceptions.TaskNotFoundException;
import com.lbycpd2.todoexp.restful.user.exceptions.UserAlreadyInDatabaseException;
import com.lbycpd2.todoexp.restful.user.exceptions.UserNotFoundException;
import com.lbycpd2.todoexp.restful.user.tasks.child.ChildModelAssembler;
import com.lbycpd2.todoexp.restful.user.tasks.child.ChildTask;
import com.lbycpd2.todoexp.restful.user.tasks.parent.ParentModelAssembler;
import com.lbycpd2.todoexp.restful.user.tasks.parent.ParentTask;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/users")
public class UserAdminController {

    private final UserService userService;
    private final UserModelAssembler userModelAssembler;
    private final ParentModelAssembler parentModelAssembler;
    private final ChildModelAssembler childModelAssembler;

    // admin facing

    @GetMapping
    public CollectionModel<EntityModel<User>> getAllUsers(){
        List<EntityModel<User>> users = userService
                .getUsers()
                .stream()
                .map(userModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(users, linkTo(methodOn(UserService.class).getUsers()).withSelfRel());
    }

    @GetMapping(path="{id}")
    public EntityModel<User> getUser(@PathVariable(name = "id") String id){
        User user = userService.getUser(id);
        return userModelAssembler.toModel(user);
    }


    @GetMapping(path="{id}/tasks")
    public CollectionModel<EntityModel<ParentTask>> getUserParentTasks(@PathVariable(name = "id") String id){
        User currentUser = userService.getUser(id);
        List<EntityModel<ParentTask>> parentTasks = currentUser
                .getParentTaskList()
                .stream()
                .map(parentModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(parentTasks, linkTo(methodOn(UserService.class).getParentTasks()).withSelfRel());
    }


    @GetMapping(path = "{id}/{parent_id}")
    public EntityModel<ParentTask> getParentTask(@PathVariable(name="id") String user_id,
                                                 @PathVariable(name = "parent_id") String parent_id) throws TaskNotFoundException, UserNotFoundException {
        ParentTask ptask = userService.getParentTask(user_id, parent_id);
        return parentModelAssembler.toModel(ptask);
    }

    @SneakyThrows
    @GetMapping(path ="{id}/{parent_id}/subtasks")
    public CollectionModel<EntityModel<ChildTask>> getChildTasks(@PathVariable(name = "id") String user_id,
                                                                 @PathVariable(name = "parent_id") String parent_id){
        List<EntityModel<ChildTask>> childrenTasks = userService
                .getParentTask(user_id, parent_id)
                .getChildTasks()
                .stream()
                .map(childModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(childrenTasks,
                linkTo(methodOn(UserService.class).getChildTasks(user_id, parent_id)).withSelfRel()
        );
    }

    @GetMapping(path="{id}/{parent_id}/{child_id}")
    public EntityModel<ChildTask> getChildTask(@PathVariable(name = "id") String user_id,
                                               @PathVariable(name = "parent_id") String parent_id,
                                               @PathVariable(name = "child_id") String child_id) throws UserNotFoundException, TaskNotFoundException {

        ChildTask childTask = userService.getChildTask(user_id, parent_id, child_id);
        return childModelAssembler.toModel(childTask);
    }
    // admin facing
}


