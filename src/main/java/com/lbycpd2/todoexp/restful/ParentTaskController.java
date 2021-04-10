package com.lbycpd2.todoexp.restful;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/users/{user_id}/maintasks")
public class ParentTaskController {
    private final MainService service;

    public ParentTaskController(MainService service) {
        this.service = service;
    }

    @PostMapping(path = "{parent_id}")
    public void addChildTask(@PathVariable Long user_id, @PathVariable Long parent_id, @RequestBody ChildTask childTask){
        service.addNewChildTask(user_id, parent_id, childTask);
    }

    @DeleteMapping(path = "{parent_id}/{child_id}")
    public void deleteChildTask(@PathVariable Long user_id, @PathVariable Long parent_id, @PathVariable Long child_id){
        service.deleteChildTask(user_id, parent_id, child_id);
    }

}
