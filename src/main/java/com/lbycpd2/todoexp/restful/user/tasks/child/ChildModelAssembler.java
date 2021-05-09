package com.lbycpd2.todoexp.restful.user.tasks.child;

import com.lbycpd2.todoexp.restful.user.UserController;
import lombok.SneakyThrows;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ChildModelAssembler implements RepresentationModelAssembler<ChildTask, EntityModel<ChildTask>> {
    @SneakyThrows
    @Override
    public EntityModel<ChildTask> toModel(ChildTask entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(UserController.class).getChildTasks(entity.getParenttask().getUserId(),
                        entity.childId)).withSelfRel(),
                linkTo(methodOn(UserController.class).getChildTask(entity.getParenttask().getUserId(),
                        entity.getParenttask().getParentId(),
                        entity.getChildId())).withRel("children_tasks"));
    }
}

