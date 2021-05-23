package com.lbycpd2.todoexp.restful.user.tasks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.MappedSuperclass;
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
    private Integer experience;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime dueDate;

    public Task() {
    }

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = false;
        this.experience = Math.abs(10 + (int) (new Random().nextFloat() * (10 - 500)));
    }
}
