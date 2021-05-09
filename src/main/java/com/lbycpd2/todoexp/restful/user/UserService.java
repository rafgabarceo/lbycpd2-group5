package com.lbycpd2.todoexp.restful.user;

import com.lbycpd2.todoexp.restful.security.email.EmailSenderService;
import com.lbycpd2.todoexp.restful.user.exceptions.TaskNotFoundException;
import com.lbycpd2.todoexp.restful.user.exceptions.UserAlreadyInDatabaseException;
import com.lbycpd2.todoexp.restful.user.exceptions.UserNotFoundException;
import com.lbycpd2.todoexp.restful.user.registration.confirmationtoken.ConfirmationToken;
import com.lbycpd2.todoexp.restful.user.registration.confirmationtoken.ConfirmationTokenService;
import com.lbycpd2.todoexp.restful.user.tasks.child.ChildRepository;
import com.lbycpd2.todoexp.restful.user.tasks.child.ChildTask;
import com.lbycpd2.todoexp.restful.user.tasks.parent.ParentTask;
import com.lbycpd2.todoexp.restful.user.tasks.parent.ParentTaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    public final UserRepository userRepository;
    public final ParentTaskRepository parentRepository;
    private final ChildRepository childRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    public final EmailSenderService emailSenderService;

    public User getUser(String userId) throws UsernameNotFoundException {
        return userRepository.findById(userId).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
    }

    public User getUserByEmail(String email){
        return userRepository.findUserByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
    }

    public void addNewUser(User user) throws UserAlreadyInDatabaseException, MessagingException {
        if(userRepository.findUserByEmail(user.getEmail()).isPresent()){
            throw new UserAlreadyInDatabaseException("User with email" + user.getEmail() + " already exists.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        // generate confirmation token
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        emailSenderService.sendConfirmationEmail(user.getEmail(), confirmationToken.getConfirmationToken());
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

    public void addParentTask(User user, ParentTask parentTask){
        try {
            user.addParentTask(parentTask);
            updateUser(user);
        } catch (Exception e){
            System.out.println("Unable to add user with exception" + e.getMessage());
        }

    }

    public void setStatus(User user, ParentTask parentTask){
        user.getParentTaskList().get(user.getParentTaskList()
        .indexOf(parentTask)).setStatus(!parentTask.isStatus());
        userRepository.save(user);
    }

    public void setDeadline(User user, ParentTask parentTask, String date){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, dateTimeFormatter);
        user.getParentTaskList().get(user.getParentTaskList().indexOf(parentTask)).setDueDate(dateTime);
        userRepository.save(user);
    }

    public void setTitle(User user, ParentTask parentTask, String title){
        user.getParentTaskList().get(user.getParentTaskList().indexOf(parentTask)).setTitle(title);
        userRepository.save(user);
    }

    public void setDescription(User user, ParentTask parentTask, String description){
        user.getParentTaskList().get(user.getParentTaskList().indexOf(parentTask)).setTitle(description);
        userRepository.save(user);
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

    public void addChildTask(User user, ParentTask parentTask, ChildTask childTask){
        try {
            parentTask.addChildTask(childTask);
            updateUser(user);
        } catch (Exception e){
            System.out.println("Unable to add user with exception" + e.getMessage());
        }
    }

    public void updateUser(User user){
        userRepository.save(user);
    }

    public boolean checkIfEmailTaken(String email){
        return userRepository.findUserByEmail(email).isPresent();
    }

    // For email

}
