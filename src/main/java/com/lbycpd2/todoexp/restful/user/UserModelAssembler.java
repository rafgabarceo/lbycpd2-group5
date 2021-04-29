package com.lbycpd2.todoexp.restful.user;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {
    @Override
    public EntityModel<User> toModel(User user) {
        return EntityModel.of(user,
                linkTo(methodOn(UserAdminController.class).getUser(user.getUser_id())).withSelfRel(),
                linkTo(methodOn(UserAdminController.class).getAllUsers()).withRel("users"),
                linkTo(methodOn(UserAdminController.class).getUserParentTasks(user.getUser_id())).withRel("parent_tasks"));
    }
}
