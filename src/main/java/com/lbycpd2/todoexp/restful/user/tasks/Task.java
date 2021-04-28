package com.lbycpd2.todoexp.restful.user.tasks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@AllArgsConstructor
@Getter
@Setter
public class Task {
    private String title;
    private String description;
    private boolean status;

    public Task() {
    }

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = false;
    }
}
