package com.lbycpd2.todoexp.restful.taskpackage;

import com.lbycpd2.todoexp.restful.userpackage.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ParentTaskService {
    private final ParentTaskRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public ParentTaskService(ParentTaskRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    // TODO: Get all parent tasks
    public List<ParentTask> getAllParentTasks(){
        return repository.findAll();
    }

    // TODO: Get specific parent task
    public Optional<ParentTask> getParentTask(Long parent_id){
        return repository.findById(parent_id);
    }

    // TODO: Delete parent task
    public void deleteParentTask(Long parent_id){
        repository.deleteById(parent_id);
    }

    // TODO: Update parent task


}
