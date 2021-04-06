package com.lbycpd2.todoexp.restful;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class User {
    @Id @GeneratedValue Long userId;

    private String username;
    private String email;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    private Tasks todoList;

    private Double experience;

    public User() {
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

    public Tasks getTodoList() {
        return todoList;
    }

    public void setTodoList(Tasks todoList) {
        this.todoList = todoList;
    }

    public Tasks getAllToDo() {
        return this.todoList;
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
