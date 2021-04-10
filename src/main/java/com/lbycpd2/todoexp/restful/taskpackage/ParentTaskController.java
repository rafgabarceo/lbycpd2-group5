package com.lbycpd2.todoexp.restful.taskpackage;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users/{user_id}/maintasks")
public class ParentTaskController {
    private final ParentTaskService parentTaskService;

    public ParentTaskController(ParentTaskService parentTaskService){
        this.parentTaskService = parentTaskService;
    }

    @GetMapping
    public List<ParentTask> getParentTasks(){
        return parentTaskService.getAllParentTasks();
    }

    @GetMapping(path="{parent_id}")
    public ParentTask getParentTask(@PathVariable("parent_id") Long parent_id){
        return parentTaskService.getParentTask(parent_id);
    }

    @DeleteMapping(path="{parent_id}")
    public void deleteParentTask(@PathVariable("parent_id") Long parent_id){
        parentTaskService.deleteParentTask(parent_id);
    }
}
