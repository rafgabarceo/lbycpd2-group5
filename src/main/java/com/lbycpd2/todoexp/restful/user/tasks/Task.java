package com.lbycpd2.todoexp.restful.user.tasks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

@MappedSuperclass
@AllArgsConstructor
@Getter
@Setter
public class Task {
    private String title;
    private String description;
    private boolean status;
    private int experience;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDate dueDate;

    public Task() {
    }

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = false;
        this.experience = Math.abs(10 + (int) (new Random().nextFloat() * (10 - 200)));
    }

    public Task(String title, String description, String date){
        LocalDate dateTime = LocalDate.parse(date);
        this.title = title;
        this.description = description;
        this.dueDate = dateTime;
        this.experience = 0;
    }
}
