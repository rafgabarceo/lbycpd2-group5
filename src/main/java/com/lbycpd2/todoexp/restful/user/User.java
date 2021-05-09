package com.lbycpd2.todoexp.restful.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lbycpd2.todoexp.restful.user.badge.Badge;
import com.lbycpd2.todoexp.restful.user.tasks.parent.ParentTask;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "client_user")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Setter(AccessLevel.NONE)
    @Column(name = "user_id")
    private String user_id;

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String authorities;
    private Boolean locked = false;
    private Boolean enabled = false;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ParentTask> parentTaskList = new LinkedList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Badge> userBadges = new LinkedList<>();

    private Double experience = 0.00;

    public User(String authorities, String email, String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
        this.experience = 0.00;
    }

    // as suggested from https://stackoverflow.com/questions/53647672/how-to-save-parent-and-child-in-one-shot-jpa-hibernate
    public void addParentTask(ParentTask parentTask){
        parentTask.setUser(this);
        this.parentTaskList.add(parentTask);
    }
}
