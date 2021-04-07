/*
*
* This will contain a collection of children tasks
*
* */
package com.lbycpd2.todoexp.restful;

import net.bytebuddy.dynamic.scaffold.MethodGraph;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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
