package com.lbycpd2.todoexp.restful;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clientUser")
public class User {
    @Id @GeneratedValue @Column(name = "user_id") Long userId;

    private String username;
    private String email;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<ParentTask> parentTaskList;

    private Double experience;

    public User() {
    }

    public List<ParentTask> getParentTaskList() {
        return parentTaskList;
    }

    public void setParentTaskList(List<ParentTask> parentTaskList) {
        this.parentTaskList = parentTaskList;
    }

    public User(String username, String email) {
        this.username = username;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
