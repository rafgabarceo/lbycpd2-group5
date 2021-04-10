package com.lbycpd2.todoexp.restful.taskpackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParentTaskService {
    private final ParentTaskRepository repository;

    @Autowired
    public ParentTaskService(ParentTaskRepository repository) {
        this.repository = repository;
    }

    public List<ParentTask> getAllParentTasks(){
        return repository.findAll();
    }
    public ParentTask getParentTask(Long parent_id){
        Optional<ParentTask> optionalPTask = repository.findById(parent_id);
        return optionalPTask.orElseThrow(IllegalAccessError::new);
    }

    public void deleteParentTask(Long parent_id){
        boolean exists = repository.findById(parent_id).isPresent();
        if(!exists) throw new IllegalStateException("Parent task does not exist!");
        repository.deleteById(parent_id);
    }

}
