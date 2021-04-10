/*
*
* This will contain a collection of children tasks
*
* */
package com.lbycpd2.todoexp.restful;

import javax.persistence.*;
import java.util.List;

@Entity
public class ParentTask extends Task {
    @Id
    @GeneratedValue @Column(name = "parent_id") Long parentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "parenttask", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ChildTask> childTasks;

    public List<ChildTask> getChildTasks() {
        return childTasks;
    }

    public ParentTask(){}

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void addChildTask(ChildTask childTask){
        childTask.setParenttask(this);
        this.childTasks.add(childTask);
    }

    public void deleteChildTask(ChildTask childTask){
        this.childTasks.remove(childTask);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setChildTasks(List<ChildTask> childTasks) {
        this.childTasks = childTasks;
    }
}
