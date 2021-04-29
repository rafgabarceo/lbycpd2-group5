package com.lbycpd2.todoexp.restful.user;

import com.lbycpd2.todoexp.restful.user.exceptions.TaskNotFoundException;
import com.lbycpd2.todoexp.restful.user.exceptions.UserNotFoundException;
import com.lbycpd2.todoexp.restful.user.tasks.child.ChildRepository;
import com.lbycpd2.todoexp.restful.user.tasks.child.ChildTask;
import com.lbycpd2.todoexp.restful.user.tasks.parent.ParentTask;
import com.lbycpd2.todoexp.restful.user.tasks.parent.ParentTaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ParentTaskRepository parentRepository;
    private final ChildRepository childRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User with email " + email + " not found!"));
    }

    public User getUser(String userId) throws UsernameNotFoundException {
        return userRepository.findById(userId).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public List<ParentTask> getParentTasks(){ // TODO: try to fix this to make it pass through the user first
        return parentRepository.findAll();
    }

    public ParentTask getParentTask(String userId, String parentId) throws TaskNotFoundException, UserNotFoundException {
        User user = getUser(userId);
        Optional<ParentTask> parentTask = user
                .getParentTaskList()
                .stream()
                .filter(id -> id.getParentId().equals(parentId)).findFirst();
        if(parentTask.isEmpty()){
            throw new TaskNotFoundException("Parent task not found.");
        }

        return parentTask.get();
    }

    public List<ChildTask> getChildTasks(String userId, String parentId) throws UserNotFoundException, TaskNotFoundException {
        ParentTask parentTask = getParentTask(userId, parentId);
        return parentTask.getChildTasks();
    }

    public ChildTask getChildTask(String userId, String parentId, String childId) throws UserNotFoundException, TaskNotFoundException {
        ParentTask parentTask = getParentTask(userId, parentId);
        Optional<ChildTask> childTask = parentTask
                .getChildTasks()
                .stream()
                .filter(id -> id.getChildId().equals(childId)).findFirst();
        if(childTask.isEmpty()){
            throw new TaskNotFoundException("Child task not found");
        }

        return childTask.get();
    }
}
