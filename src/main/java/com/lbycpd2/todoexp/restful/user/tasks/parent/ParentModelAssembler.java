package com.lbycpd2.todoexp.restful.user.tasks.parent;

import com.lbycpd2.todoexp.restful.user.UserAdminController;
import lombok.SneakyThrows;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ParentModelAssembler implements RepresentationModelAssembler<ParentTask, EntityModel<ParentTask>> {
    @SneakyThrows
    @Override
    public EntityModel<ParentTask> toModel(ParentTask entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(UserAdminController.class).getParentTask(entity.getUserId(), entity.parentId)).withSelfRel(),
                linkTo(methodOn(UserAdminController.class).getUserParentTasks(entity.getUserId())).withRel("tasks"),
                linkTo(methodOn(UserAdminController.class).getUser(entity.getUserId())).withRel("owned_by")
                );
    }
}
