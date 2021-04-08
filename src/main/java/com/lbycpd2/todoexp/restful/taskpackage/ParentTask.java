/*
*
* This will contain a collection of children tasks
*
* */
package com.lbycpd2.todoexp.restful.taskpackage;

import com.lbycpd2.todoexp.restful.userpackage.User;

import javax.persistence.*;
import java.util.List;

@Entity
public class ParentTask extends Task {
    @Id
    @GeneratedValue @Column(name = "parent_id") Long parentId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "parenttask", orphanRemoval = true)
    private List<ChildTask> childTasks;

    public ParentTask(){}
}
