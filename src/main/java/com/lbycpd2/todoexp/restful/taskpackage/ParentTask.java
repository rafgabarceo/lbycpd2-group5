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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "parenttask", orphanRemoval = true)
    private List<ChildTask> childTasks;

    public ParentTask(){}

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ChildTask> getChildTasks() {
        return childTasks;
    }

    public void setChildTasks(List<ChildTask> childTasks) {
        this.childTasks = childTasks;
    }
}
