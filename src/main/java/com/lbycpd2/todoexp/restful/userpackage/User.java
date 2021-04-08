package com.lbycpd2.todoexp.restful.userpackage;

import com.lbycpd2.todoexp.restful.taskpackage.ParentTask;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clientUser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long user_id;

    private String username;
    private String password;
    private String email;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<ParentTask> parentTaskList;

    private Double experience;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    public List<ParentTask> getParentTaskList() {
        return parentTaskList;
    }

    public void setParentTaskList(List<ParentTask> parentTaskList) {
        this.parentTaskList = parentTaskList;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.experience = 0.00;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getExperience() {
        return experience;
    }

    public void setExperience(Double experience) {
        this.experience = experience;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

}
