package com.lbycpd2.todoexp.restful.taskpackage;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Task {
    private String title;
    private String description;
    private boolean status;

    public Task() {
    }

    public Task(String title, String description, boolean status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
