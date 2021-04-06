package com.lbycpd2.todoexp.restful;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="users")
public class User {
    @Id @GeneratedValue Long id;

    private String username;
    private String email;
    private Tasks todoList;
    private Double experience;
    private Badges badges;

    public User() {
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.experience = 0.00;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Task> getAllToDo() {
        return this.todoList;
    }

    public Double getExperience() {
        return experience;
    }

    public void setExperience(Double experience) {
        this.experience = experience;
    }

    public Badges getBadges() {
        return badges;
    }

    public void setBadges(Badges badges) {
        this.badges = badges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(todoList, user.todoList) && Objects.equals(experience, user.experience) && Objects.equals(badges, user.badges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, todoList, experience, badges);
    }
}
