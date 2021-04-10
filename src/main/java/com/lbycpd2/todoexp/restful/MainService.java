package com.lbycpd2.todoexp.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.Optional;

@Service
public class MainService {
    private final UserRepository userRepository;
    private final ParentTaskRepository parentRepository;
    private final ChildRepository childRepository;

    @Autowired
    public MainService(UserRepository userRepository, ParentTaskRepository parentRepository, ChildRepository childRepository) {
        this.userRepository = userRepository;
        this.parentRepository = parentRepository;
        this.childRepository = childRepository;
    }

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping
    public User getUser(Long userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElseThrow(IllegalAccessError::new);
    }

    public void addUser(User newUser){
        Optional<User> userOptional = userRepository.findUserByEmail(newUser.getEmail());
        if(userOptional.isPresent()) {
            throw new IllegalStateException("Email is already taken.");
        }
        userRepository.save(newUser);
    }

    private void saveUser(Long user_id){
        userRepository.save(userRepository.getOne(user_id));
    }

    public void deleteUser(Long userId){
        boolean exists = userRepository.existsById(userId);
        if(!exists) throw new IllegalStateException("User with id " + userId + " does not exist.");
        userRepository.deleteById(userId);
    }

    public void addNewParentTask(Long user_id, ParentTask parentTask){
        userRepository.getOne(user_id).addParentTask(parentTask);
        saveUser(user_id);
    }

    public void deleteParentTask(Long user_id, Long parent_id){
        Optional<ParentTask> optionalParentTask = parentRepository.findById(parent_id);
        userRepository.getOne(user_id).deleteParentTask(optionalParentTask.orElseThrow(IllegalAccessError::new));
        saveUser(user_id);
    }

    public void addNewChildTask(Long user_id, Long parent_id, ChildTask childTask){
        parentRepository.getOne(parent_id).addChildTask(childTask);
        saveUser(user_id);
    }

    public void deleteChildTask(Long user_id, Long parent_id, Long child_id){
        parentRepository.getOne(parent_id).deleteChildTask(childRepository.getOne(child_id));
        saveUser(user_id);
    }

    public void updateUser(Long userId, String username, String password, String email, Double experience){
        boolean exists = userRepository.existsById(userId);
        if(!exists) throw new IllegalStateException("User " + username + " with user id " + userId + " does not exist.");

        User userUpdate = userRepository.getOne(userId);

        if(username != null && username.length() > 0 && !username.equals(userUpdate.getUsername())) userUpdate.setUsername(username);
        if(email != null && email.length() > 0 && !email.equals(userUpdate.getEmail())){
            if(userRepository.findUserByEmail(email).isPresent()) {
                throw new IllegalStateException("Email already in database.");
            }
            userUpdate.setEmail(email);
        }
        if(password != null && !password.equals(userUpdate.getPassword())) userUpdate.setPassword(password);
        if(experience > 0) userUpdate.setExperience(userUpdate.getExperience() + experience);
    }
}
