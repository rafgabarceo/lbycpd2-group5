package com.lbycpd2.todoexp.restful.tasks.child;

import com.lbycpd2.todoexp.restful.tasks.Task;

import javax.persistence.*;

@Entity
public class ChildTask extends Task {

    @Id @GeneratedValue @Column(name = "child_id") Long childId;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private ParentTask parenttask;

    public ChildTask(Long childId, ParentTask parenttask) {
        this.childId = childId;
        this.parenttask = parenttask;
    }

    public ChildTask(){}

    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }

    public void setParenttask(ParentTask parenttask) {
        this.parenttask = parenttask;
    }
}
